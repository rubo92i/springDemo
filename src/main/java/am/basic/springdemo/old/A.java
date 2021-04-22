package am.basic.springdemo.old;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class A {




    //this  method will work after 15 seconds  every second
    @Scheduled(initialDelay =  15000 ,fixedRate = 1000000)
    public void test(){
       log.info("Scheduled is working");
    }
}
