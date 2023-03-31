package com.example.reactiveweb.repository;

import com.example.reactiveweb.entity.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users,Long> {
}
