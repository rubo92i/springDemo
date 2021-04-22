package am.basic.springdemo;


import am.basic.springdemo.model.User;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextProvider {


    public User getByAuthentication() {
        User user = new User();
        user.setId(1);
        return user;
    }


}
