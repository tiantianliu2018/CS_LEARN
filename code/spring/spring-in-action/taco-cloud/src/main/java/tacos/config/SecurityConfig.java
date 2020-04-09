package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import tacos.service.UserDetailsService;

/**
 * @author liutiantian
 * @create 2020-04-08 11:21
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")

            .and()
                .formLogin() // 配置自定义登录表单
                    .loginPage("/login")
                    //.loginProcessingUrl("/authenticate")
                    //.usernameParameter("user")
                    //.passwordParameter("pwd")
            //.and()
                //.formLogin()
                   // .loginPage("/login")
                    //.defaultSuccessUrl("/design", true)
            .and()
                .logout()
                    .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(encoder());

        /**
         *
        基于 ldpa
        auth.ldapAuthentication()
                .userSearchBase("ou=people") // 查找用户的基本查询
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("passcode");
//                .contextSource()
//                .root("dc=tacocloud,dc=com");
         */

        // 基于 JDBC
        /**
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from Users " +
                        "where username=?")
                .authoritiesByUsernameQuery("select username, authority from UserAuthorities " +
                        "where username=?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
         **/

        // 内存用户存储
//        auth.inMemoryAuthentication()
//                .withUser("buzz")
//                    .password("123")
//                    .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                    .password("456")
//                    .authorities("ROLE_USER");
    }
}
