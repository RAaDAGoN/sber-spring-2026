package org.radagon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeComponent {

    @Value("${spring.application.version}")
    private String AppVersion;

    public String getAppVersion() {
        return AppVersion;
    }
}
