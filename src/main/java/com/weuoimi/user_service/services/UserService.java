package com.weuoimi.user_service.services;

import com.weuoimi.user_service.domain.UserDomain;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    // Register a new user
    Mono<UserDomain> registerUser(UserDomain user);

    // Log in a user
    Mono<UserDomain> loginUser(UserDomain user);

    // Get user by ID
    Mono<UserDomain> getUserById(UUID userId);

    // Update user details
    Mono<UserDomain> updateUser(UserDomain user);

    // Delete user by ID
    Mono<Void> deleteUser(UUID userId);
}

