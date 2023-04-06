package com.example.reactiveweb.service;

import com.example.reactiveweb.entity.Email;
import com.example.reactiveweb.entity.Users;
import com.example.reactiveweb.repository.EmailRepository;
import com.example.reactiveweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;

    @Autowired
    public UserService(UserRepository userRepository, EmailRepository emailRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
    }

    public Flux<Users> getAllUses() {
        return userRepository
                .findAll()
                .flatMap(users -> {
                    return emailRepository.findAllById(users.getEmailIds()).map(email -> {
                        users.addEmail(email);
                        return users;
                    }).last();
                });
    }

    public Mono<Users> saveUser(Mono<Users> user) {
        return user
                .flatMap(u -> {
                    List<Email> emailList = u.getEmails();
                    u.setEmails(new ArrayList<>());
                    return emailRepository.saveAll(emailList)
                            .map(savedEmail -> {
                                u.addEmail(savedEmail);
                                return u;
                            })
                            .last();
                })
                .flatMap(u -> userRepository.save(u));
    }

    public Flux<String> getEmailByUsername(Flux<String> username) {
        Flux<Users> users =username.flatMap(name->userRepository.findByName(Mono.just(name)));
        Flux<Email> emailFlux = users.flatMap(user -> emailRepository.findAllById(user.getEmailIds()));
        return emailFlux.delayElements(Duration.ofSeconds(2)).map(email -> email.getEmail());
    }

}
