package org.radagon;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
public class GeneraterComponent {

    public void GenerateGUID () {
        System.out.println("ID: " + System.identityHashCode(this) + " GUID: " + UUID.randomUUID().toString());
    }
}
