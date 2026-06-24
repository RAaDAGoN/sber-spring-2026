package org.radagon.radagonHelloTask;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {

    public void sayHello() {
        System.out.println("Hello, магия вне хогвартса!");
    }
}
