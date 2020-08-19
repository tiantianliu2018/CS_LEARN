from selenium import webdriver
# 要想调用键盘按键操作需要引入keys包
from selenium.webdriver.common.keys import Keys
from selenium.webdriver import ActionChains
from bs4 import BeautifulSoup
import requests
import time
import json
import os

class QQMusic:
    def __init__(self):
        self.headers = {
            'user-agent':'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36',
        }
        self.url = 'https://y.qq.com/portal/mv_lib.html#page={page_id}&order=0'
        self.mv_url_base = 'https://u.y.qq.com/cgi-bin/musicu.fcg'
        self.post_params_dict = {
            "getMvUrl":{
                "module":"gosrf.Stream.MvUrlProxy",
                "method":"GetMvUrls",
                "param":{
                    "vids":[],
                    "request_typet":10001,"addrtype":3
                    }
            }
        }
        self.res_path = 'mv/'
        # 创建浏览器对象
        self.driver = webdriver.Chrome()
        
        
    def init_selenium(self):
        self.driver.maximize_window()

    def close(self):
        self.driver.close()
        self.driver.quit()

    def next_page(self):
        next_page = self.driver.find_element_by_xpath('//*[@class="next js_pageindex"]').click()
        time.sleep(2)     
        print("============== next page ==============")

        

    def get_vid_title(self, page_id):
        self.driver.get(self.url.format(page_id = page_id))
        time.sleep(2)
        ul = self.driver.find_element_by_xpath('//*[@class="mod_mv"]/ul')
        lis = ul.find_elements_by_xpath('li')
        mv_list = []
        for li in lis:
            vid = li.get_attribute("data-vid")
            title = li.text.split("\n")[0]
            mv_list.append([vid, title])
        return mv_list
    

    ## 根据 vid 获取每一个视频的真实地址
    def get_QQMusic_MV_url(self, mv_list):
        mv_real_addr_list = []
        for item in mv_list:
            vid = item[0]
            title = item[1]
            print(title + "\t" + vid)

            self.post_params_dict["getMvUrl"]["param"]["vids"].append(vid)
            mv_response = requests.post(self.mv_url_base, data = json.dumps(self.post_params_dict)).text
            mv_response_json = json.loads(mv_response)

            # print(mv_response_json)
    
            freeflow_url = mv_response_json['getMvUrl']['data'][vid]['mp4'][-2]['freeflow_url']
            if freeflow_url:    
                mv_real_addr = freeflow_url[-1]
                # print(mv_real_addr)
                mv_real_addr_list.append([mv_real_addr, vid])

        return mv_real_addr_list

            # self.get_mv_video(mv_real_addr, vid)
            # print("===============================\n")
        

    def get_mv_video(self, mv_real_link, vid):
        video = requests.get(mv_real_link, headers=self.headers).content
        with open(self.res_path + vid + ".mp4" , "wb") as f:
            print("正在下载：" + vid)
            f.write(video)


    def download_MVs(self):
        self.init_selenium()   ## 初始化 selenium

        for i in range(self.max_pages):
            mv_list = self.get_vid_title(i)  ## 获取每一页的 vid 和 title
            self.get_QQMusic_MV(mv_list)
            ## 自动翻页
            next_page = self.driver.find_element_by_xpath('//*[@class="next js_pageindex"]').click()
            time.sleep(2)
            
            print("============== next page ==============")


class FFmpeg:
    def __init__(self):
        self.src_path = "mv"
        self.picture_path = "mv_pictures1/"
        self.music_path = "mv_music1"
        self.command = "-vf 'select=eq(pict_type\,I)'  -vsync vfr -qscale:v 2 -f image2"

    def extract_frames(self):
        for video_name in os.listdir(self.src_path):
            filename = self.src_path + "\\" + video_name
            print("filename: " + filename)
            name = video_name.split(".")[0]
            pic_dest = self.picture_path + name + "\\"
            music_dest = self.music_path + "\\"
            
            if not os.path.exists(pic_dest):
                os.mkdir(pic_dest)

            if not os.path.exists(music_dest):
                os.mkdir(music_dest)

            ## 抽取关键帧
            os.system('ffmpeg -i %s -vf "select=eq(pict_type\,I)" -vsync vfr -qscale:v 2 -f image2 %s%%04d.jpg' %(filename, pic_dest))

            ## 图片去除水印
            # os.system('ffmpeg -i %s -strict -2 -vf delogo=x=300:y=250:w=56:h=18:show=0 %s.jpeg' %())

            ## 音视频分离
            os.system("ffmpeg -i %s -vn -y -acodec copy %s%s.m4a" %(filename, music_dest, name)) 

            os.system('del %s' %filename)

            

if __name__ == "__main__":
    qq = QQMusic()
    fm = FFmpeg()

    qq.init_selenium()
    max_pages = 50

    count = 0

    for i in range(48, max_pages):
        mv_list = qq.get_vid_title(i)  ## 获取每一页的 vid 和 title
        print('====================== mv list : ', len(mv_list))
        mv_real_addr_list = qq.get_QQMusic_MV_url(mv_list)
        # print('====================== mv address : ', len(mv_real_addr_list))
        # print(mv_real_addr_list[0])
        for item in mv_real_addr_list:
            url = item[0]
            vid = item[1]
            qq.get_mv_video(url, vid)   ## 获取每一个视频
            time.sleep(2)
            fm.extract_frames()   ## 抽帧等

            count += 1
            if count % 100 == 0:
                print("Now down load: %d: %s" % (count-1, vid))

        ## 自动翻页

        

    qq.close()
    # qq.download_MVs()