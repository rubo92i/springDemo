package am.basic.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true,prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers("/api/sign-up","/api/login","/api/verify","/api/resend").permitAll()
                .antMatchers("/api/search").denyAll()
                .antMatchers(HttpMethod.POST,"/card/**").authenticated()
                .antMatchers("/test1").hasRole("ADMIN")
                .anyRequest().authenticated();

    }


}
