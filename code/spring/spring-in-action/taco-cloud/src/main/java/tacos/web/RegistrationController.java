package tacos.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.domian.UserRepository;
import tacos.domian.entity.RegistrationForm;

/**
 * @author liutiantian
 * @create 2020-04-08 20:07
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    public String processRegistration(RegistrationForm form){
        userRepository.save(form.toUser(encoder));
        return "redirect:/login";
    }



}
