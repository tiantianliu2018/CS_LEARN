package tacos.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liutiantian
 * @create 2020-04-06 17:16
 */
@WebMvcTest
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception{
        // 从 MockMvc 对象开始，执行针对 /（根路径）的 HTTP GET 请求。
        // 该请求规定了下列期望值：
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())  // 响应应该有一个HTTP 200（OK）状态
                .andExpect(view().name("home")) // 视图应该有一个合理的主页名称
                .andExpect(content().string(containsString("Welcome to..."))); // 呈现的视图应该包含 “Welcome to...”
    }


}