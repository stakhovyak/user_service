package com.weuoimi.user_service.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.weuoimi.user_service.domain.UserDomain;
import com.weuoimi.user_service.exception.UserAccountNotFoundException;
import com.weuoimi.user_service.repo.UserRepository;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDomain> registerUser(UserDomain user) {
        // You might want to include additional logic, such as validation, password hashing, etc.
        return userRepository.save(user)
            .doOnSuccess(savedUser -> log.info("User registered with ID: {}", savedUser.getId()))
            .doOnError(error -> log.error("Error registering user", error));
    }

    @Override
    public Mono<UserDomain> loginUser(UserDomain user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())
                            .switchIfEmpty(Mono.error(new UserAccountNotFoundException(/* user.getEmail().toString() */)))
                            .doOnError(error -> log.error("Error during user login with email: {}", user.getEmail()));
    }

    @Override
    public Mono<UserDomain> getUserById(UUID userId) {
        return userRepository.findById(userId)
                            .switchIfEmpty(Mono.error(new UserAccountNotFoundException(/* userId.toString() */)))
                            .doOnError(error -> log.error("Error during attempt at finding user by Id: {}", userId.toString()));
    }

    @Override
    public Mono<UserDomain> updateUser(UserDomain user) {
        return userRepository.findById(user.getId())
                            .switchIfEmpty(Mono.error(new UserAccountNotFoundException(/*user.getId().toString() */)))
                            .flatMap(existingUser -> {
                                existingUser.setEmail(user.getEmail());
                                existingUser.setFirstName(user.getFirstName());
                                existingUser.setLastName(user.getLastName());
                                existingUser.setUpdatedAt(LocalDateTime.now());

                                return userRepository.save(existingUser);
                            });
    }

    @Override
    public Mono<Void> deleteUser(UUID userId) {
        return userRepository.deleteById(userId);
    }
}