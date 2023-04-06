package com.example.rsocket_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ClientConsole {
    @Autowired
    RSocketRequester.Builder builder;

    public void sendInfo() {
        //RSocketRequester rSocketRequester = builder.tcp("localhost", 7000);
        RSocketRequester rSocketRequester = builder.websocket(URI.create("ws://localhost:8081/rsocket"));

        Flux<String> usernameFlux = Flux.fromArray(new String[]{"UserFromApi_withNewName","username 1"}).delayElements(Duration.ofSeconds(1));


        rSocketRequester
                .route("UserInformation")
                .data(usernameFlux)
                .retrieveFlux(String.class)
                .subscribe(s -> System.out.println("!!!!!!!!" + LocalDateTime.now() + " / " + s));

        try {
            Thread.sleep(30*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
