package com.example.reactiveweb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Email {
    @Id
    private Long id;
    private @NonNull String email;
}
