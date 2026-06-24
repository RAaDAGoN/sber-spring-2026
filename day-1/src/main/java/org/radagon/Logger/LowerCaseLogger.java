package org.radagon.Logger;

import org.springframework.stereotype.Component;

@Component("LowLogger")
public class LowerCaseLogger implements Logger{
    @Override
    public void log(String message) {
        System.out.println(message.toLowerCase());
    }
}
