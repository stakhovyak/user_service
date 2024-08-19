package com.weuoimi.user_service.repo;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.weuoimi.user_service.domain.UserDomain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserPostgreRepository {

    private final DatabaseClient databaseClient;
    private final R2dbcEntityTemplate template;

    @Override 
    public Mono<UserDomain> findByEmailAndPassword(String email, String password) {
        // TODO: implement
        return Mono.empty();
    }
}
