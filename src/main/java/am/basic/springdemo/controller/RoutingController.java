package am.basic.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutingController {


    @RequestMapping("/go-to-sign-up")
    public String goToSignUp() {
        return "sign-up";
    }




}
