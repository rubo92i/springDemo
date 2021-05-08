package am.basic.springdemo.config;


import am.basic.springdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SecurityContextProvider {


    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;


     public User getByAuthentication(OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additional = resourceServerTokenServices.readAccessToken(readTokenValue(oAuth2Authentication)).getAdditionalInformation();
        User user = new User();
        user.setId((Long) additional.get("userId"));
        user.setUsername(additional.get("username").toString());
        user.setName(additional.get("name").toString());
        return user;
    }

    public String readTokenValue(OAuth2Authentication auth2Authentication) {
        return ((OAuth2AuthenticationDetails) auth2Authentication.getDetails()).getTokenValue();
    }
}
