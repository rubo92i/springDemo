package am.basic.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;


    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/sign-up", "/api/login", "/api/verify", "/api/resend").permitAll()
                .antMatchers("/api/search").denyAll()
                .antMatchers(HttpMethod.POST, "/card/**").authenticated()
                .antMatchers("/test1").hasRole("ADMIN")
                .anyRequest().authenticated();

    }



    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // UserDetails
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());


    }


//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // UserDetails
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username,password,true as enabled from user where username = ?")
//                .authoritiesByUsernameQuery("SELECT u.username,r.name FROM `user` u ,role r, user_role ru WHERE   ru.user_id = u.id AND r.id = ru.role_id  AND u.`username` = ? ")
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//
//    }


//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//       // UserDetails
//        auth.inMemoryAuthentication()
//                .withUser("someUser").password("somePassword").roles("USER").and()
//                .withUser("someAdmin").password("adminPassword").roles("ADMIN").and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//
//    }

}
