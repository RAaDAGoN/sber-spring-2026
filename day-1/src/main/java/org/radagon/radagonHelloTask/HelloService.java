package org.radagon.radagonHelloTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    public HelloService(@Autowired HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public void execute() {
        helloRepository.sayHello();
    }
}
