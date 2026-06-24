package org.radagon.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    @Qualifier("LowLogger")
//    @Qualifier("UppLogger")
    @Autowired
    private Logger logger;

    public void execute(String message){
        logger.log(message);
    }
}
