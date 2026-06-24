package org.radagon;

import org.radagon.Logger.LoggerService;
import org.radagon.radagonHelloTask.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.radagon");

//        HelloService service = context.getBean(HelloService.class);
//        service.execute();

        LoggerService loggerService = (LoggerService) context.getBean("loggerService");
        loggerService.execute("Это моё сообщение для логгера");
    }
}
