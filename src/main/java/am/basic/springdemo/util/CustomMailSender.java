package am.basic.springdemo.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CustomMailSender {


    @Autowired
    private JavaMailSender javaMailSender;


    @Async
    public void sendMail(String title, String content, String to) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(content);
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
}
