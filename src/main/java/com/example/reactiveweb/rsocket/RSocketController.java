package com.example.reactiveweb.rsocket;

import com.example.reactiveweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RSocketController {
    private final UserService userService;

    @Autowired
    public RSocketController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("UserInformation")
    public Flux<String> findEmailByUsername(Flux<String> username) {
        return userService.getEmailByUsername(username);
    }
}
