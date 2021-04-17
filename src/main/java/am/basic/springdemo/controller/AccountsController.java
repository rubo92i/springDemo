package am.basic.springdemo.controller;


import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.User;
import am.basic.springdemo.model.excpetion.ForbiddenException;
import am.basic.springdemo.model.excpetion.NotFoundException;
import am.basic.springdemo.service.UserService;
import am.basic.springdemo.util.CookieUtil;
import am.basic.springdemo.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AccountsController {


    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public ModelAndView start(@SessionAttribute(required = false) User user,
                              @CookieValue(required = false) String username,
                              @CookieValue(required = false) String password,
                              HttpSession httpSession, HttpServletResponse response) throws ResponseException {


        if (user != null) {
            return this.login(user.getUsername(), user.getPassword(), null, httpSession, response);
        }

        if (username != null && password != null) {
            return this.login(username, Encryption.decrypt(password), "remember", httpSession, response);
        }

        return new ModelAndView("index");

    }


    @PostMapping("/login")
    //@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(name = "user-i-name") String username,  //this part is get request parameters automatically, if one of parameter not exist it return 400 BAD REQUEST
                              @RequestParam(name = "user-i-pass") String password,
                              @RequestParam(required = false) String remember,  // in this case request param name and field name are same, and we can  miss parameter name in annotation, here we set required false, as it can be not sent;
                              HttpSession session, HttpServletResponse resp) throws ResponseException {

        try {
            User user = userService.signIn(username, password);
            this.checkRememberMe(username, password, remember, resp);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(500000);
            return new ModelAndView("secure/home");  // model and view class has many constructor, this one is working only with view  , and redirect user to mentioned page after method

        } catch (RuntimeException throwable) {
            return new ModelAndView("index", "message", "Something went wrong , please try later");
        }

    }


    @RequestMapping("/sign-up")
    public String signUp(@RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String username,
                         @RequestParam String password,
                         Model model) throws ResponseException {

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(password);
        try {
            userService.signUp(user);
            model.addAttribute("username", username);
            model.addAttribute("message", "You have successfully registered, please verify");
            return "verification";

        } catch (RuntimeException throwable) {
            model.addAttribute("message", "Something went wrong , please try later");
        }
        return "sign-up";
    }


    @RequestMapping("/verify")
    public ModelAndView verify(@RequestParam String username,
                               @RequestParam String code) {

        try {
            userService.verify(username, code);
            return new ModelAndView("index", "message", "You have successfully verified, please sign in");
        } catch (NotFoundException notFoundException) {
            return new ModelAndView("index", "message", "Please register for first");
        } catch (ForbiddenException e) {
            return new ModelAndView("verification")
                    .addObject("username", username)
                    .addObject("message", "Wrong verification code");
        } catch (RuntimeException throwable) {
            return new ModelAndView("verification")
                    .addObject("username", username)
                    .addObject("message", "Something went wrong , please try later");
        }
    }


    @RequestMapping("/resend")
    public ModelAndView resend(@RequestParam String username) {
        try {
            userService.resend(username);
            return new ModelAndView("verification")
                    .addObject("username", username)
                    .addObject("message", "Code was sent to your email, please check it");
        } catch (NotFoundException notFoundException) {
            return new ModelAndView("index", "message", "Please register for first");
        } catch (RuntimeException throwable) {
            return new ModelAndView("verification")
                    .addObject("username", username)
                    .addObject("message", "Something went wrong , please try later");
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse resp) {
        CookieUtil.deleteCookie("username", resp);
        CookieUtil.deleteCookie("password", resp);
        session.invalidate();
        return "index";
    }


    private void checkRememberMe(String username, String password, String remember, HttpServletResponse resp) {
        if (remember != null) {
            CookieUtil.setCookieValue("username", username, 18000, resp);
            CookieUtil.setCookieValue("password", Encryption.encrypt(password), 18000, resp);
        }
    }


}
