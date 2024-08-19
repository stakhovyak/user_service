package com.weuoimi.user_service.repo;

import com.weuoimi.user_service.domain.UserDomain;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<UserDomain, UUID>, UserPostgreRepository {
    
    // Custom query method to find a user by email and password
    // Mono<UserDomain> findByEmailAndPassword(String email, String password);

    // Other custom query methods can be added here if needed
}

