package am.basic.springdemo;

import am.basic.springdemo.util.CustomMailSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Log4j2
@EnableAsync
@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {

        CustomMailSender customMailSender = SpringApplication.run(SpringDemoApplication.class, args).getBean(CustomMailSender.class);

        long start = System.currentTimeMillis();
        log.info("Start ");
        for (int i = 0; i < 1000; i++) {
            customMailSender.sendMail("Test", "Hello " + i, "eduardhay2001@gmail.com");
        }
        log.info("1000 mails sent, duration : {}  seconds",(System.currentTimeMillis() -start)/1000);
    }


    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        return threadPoolTaskExecutor;
    }

}