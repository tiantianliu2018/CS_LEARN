import com.learn.entity.User;
import com.learn.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kelly
 * @create 2020-06-20 11:45
 */
public class TestUserService {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.findAll().forEach(System.out::println);

    }
}
