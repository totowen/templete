package com.tos;

import com.tos.StateMachineConfig.Events;
import com.tos.StateMachineConfig.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
//@EnableCaching
@EnableScheduling
@EnableAsync
public class TempleteApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testLogLevel() {
        logger.debug("Logger Level ：DEBUG");
        logger.info("Logger Level ：INFO");
        logger.error("Logger Level ：ERROR");
        return "";
    }

    /*StateMachine  START*/
    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        Message<Events> message = MessageBuilder
                .withPayload(Events.PAY)
                .setHeader("totowen", "hahaha")
                .build();
        stateMachine.sendEvent(message);
        stateMachine.sendEvent(Events.RECEIVE);
    }
    /*StateMachine  END*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TempleteApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TempleteApplication.class, args);
    }



}
