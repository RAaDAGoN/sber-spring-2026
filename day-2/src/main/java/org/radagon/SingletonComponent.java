package org.radagon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonComponent {

    @Value("${spring.application.name}")
    private String AppName;

    public String getAppName() {
        return AppName;
    }
}
