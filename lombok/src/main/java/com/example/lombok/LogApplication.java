package com.example.lombok;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogApplication implements ApplicationRunner {
    //private static final Logger log = LoggerFactory.getLogger(LogApplication.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("Entering calculatePrice()");
        log.debug("User ID: 123");
        log.info("Application started");
        log.warn("Disk space is low");
        log.error("Failed to connect to database");
    }
}
