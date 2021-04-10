package am.basic.springdemo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {


    public static String getCookieValue(String name, HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }


    public static void setCookieValue(String name, String value, int expiration, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expiration);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteCookie(String name, HttpServletResponse response){
        setCookieValue(name,null,0,response);
    }
}
