package org.radagon.Logger;

import org.springframework.stereotype.Component;

@Component("UppLogger")
public class UpperCaseLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message.toUpperCase());
    }
}
