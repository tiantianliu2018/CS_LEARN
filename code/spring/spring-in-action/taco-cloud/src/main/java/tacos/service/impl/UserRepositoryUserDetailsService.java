package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.domian.UserRepository;
import tacos.domian.entity.User;
import tacos.service.UserDetailsService;

/**
 * @author liutiantian
 * @create 2020-04-08 19:54
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null != user){
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
