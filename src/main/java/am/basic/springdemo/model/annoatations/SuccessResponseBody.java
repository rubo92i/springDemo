package am.basic.springdemo.model.annoatations;


import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResponseBody
@ResponseStatus(HttpStatus.OK)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SuccessResponseBody {



    @AliasFor(
            annotation = ResponseStatus.class
    )
    HttpStatus value() default HttpStatus.OK;

}
