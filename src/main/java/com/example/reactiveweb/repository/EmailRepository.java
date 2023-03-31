package com.example.reactiveweb.repository;

import com.example.reactiveweb.entity.Email;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends ReactiveCrudRepository<Email,Long> {
}
