package org.radagon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private SingletonComponent singletonComponent;

    @Autowired
    private PrototypeComponent prototypeComponent;

    @Autowired
    private ApplicationContext applicationContext;

    static void main(String[] args) {
        System.out.println("App started");
        SpringApplication.run(Main.class, args);
        System.out.println("App finished");
    }

    @Override
    public void run(String... args) throws Exception {
        // Практика
        System.out.println(singletonComponent.getAppName());
        System.out.println(prototypeComponent.getAppVersion());
        /*
            Вывод:
            day-2
            1.0
         */

        /*
            Результат работы PostConstruct:
            AppName: day-2
            AppVersion: 1.0
            TypeProject: NONE
         */

        // ДЗ

        for (int i = 0; i < 5; i++) {
            GeneraterComponent generaterComponent = applicationContext.getBean(GeneraterComponent.class);
            generaterComponent.GenerateGUID();
        }

        /*
            Вывод:
            ID: 577245010 GUID: f4c4f028-9724-4ce7-8fb9-d968b60850f0
            ID: 758465579 GUID: 01c37d31-d46f-4492-a463-944debc81184
            ID: 670689666 GUID: f790c5ab-8f73-4243-aa17-db5b5960cb87
            ID: 1167327028 GUID: 0d550b35-3d8c-40e3-a1eb-4d75d27b0a69
            ID: 936628786 GUID: 58b00727-5539-4fbe-be1d-8e8392257add

            Из результата консоли видно, что благодаря prototype bean - хэш генерируется уникальным
         */
    }
}
