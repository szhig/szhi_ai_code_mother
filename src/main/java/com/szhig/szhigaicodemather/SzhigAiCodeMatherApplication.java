package com.szhig.szhigaicodemather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SzhigAiCodeMatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SzhigAiCodeMatherApplication.class, args);
    }

}
