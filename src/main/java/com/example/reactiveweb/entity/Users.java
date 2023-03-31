package com.example.reactiveweb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Users {
    @Id
    private Long id;
    private @NonNull String name;
    private Set<Long> emailIds = new HashSet<>();

    @Transient
    private transient List<Email> emails = new ArrayList<>();

    public void addEmail(Email email) {
        emails.add(email);
        emailIds.add(email.getId());
    }
}
