package org.radagon;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitComponent {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.version}")
    private String appVersion;

    @Value("${spring.main.web-application-type}")
    private String typeProject;

    @PostConstruct
    private void postConstruct()
    {
        System.out.println("AppName: " + appName + "\n" + "AppVersion: " + appVersion + "\n" + "TypeProject: " +typeProject);
    }
}
