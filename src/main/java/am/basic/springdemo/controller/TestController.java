package am.basic.springdemo.controller;

import am.basic.springdemo.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {


    @RequestMapping(value = "/test1")
    public ResponseEntity test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        map.put("resultText", "samo text");
        map.put("resultInt", 15);
        map.put("resultBoolean", false);
        map.put("resultObject", new Role().withName("someRole").withId(1500));
        return ResponseEntity.status(404).body(map);
    }


    @RequestMapping(value = "/test2")
    public ResponseEntity test2() {
        return ResponseEntity.status(404).body(new Role().withName("someRole").withId(1500));
    }


    @RequestMapping(value = "/test3")
    public ResponseEntity test3(@RequestParam String param) {
        System.out.println(param);
        return ResponseEntity.status(404).body(new Role().withName(param).withId(1500));
    }

    @RequestMapping(value = "/test4")
    public ResponseEntity test4(@RequestBody Role role) {
        System.out.println(role);
        return ResponseEntity.status(404).body(role);
    }
}
