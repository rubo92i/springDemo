package am.basic.springdemo.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping
    public ResponseEntity<?> add(@RequestParam String message) {
        rabbitTemplate.convertAndSend("cucumber", message);
        return ResponseEntity.ok().build();

    }
}
