package com.weuoimi.user_service.repo;

import com.weuoimi.user_service.domain.UserDomain;

import reactor.core.publisher.Mono;

public interface UserPostgreRepository {

    Mono<UserDomain> findByEmailAndPassword(String email, String password);
}