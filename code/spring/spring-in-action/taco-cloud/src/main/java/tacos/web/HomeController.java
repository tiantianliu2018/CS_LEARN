package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liutiantian
 * @create 2020-04-06 17:09
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        // 视图的逻辑名称
        return "home";
    }
}
