package com.weuoimi.user_service.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

import java.time.Duration;

import com.weuoimi.grpc.user.service.ReactorUserServiceGrpc;
import com.weuoimi.grpc.user.service.UserServiceProto.DeleteUserRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.DeleteUserResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.GetUserByIdRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.GetUserByIdResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.LoginUserRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.LoginUserResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.RegisterUserRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.RegisterUserResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.UpdateUserRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.UpdateUserResponse;
import com.weuoimi.user_service.exception.MonoTimeoutException;
import com.weuoimi.user_service.mapper.UserMapper;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends ReactorUserServiceGrpc.UserServiceImplBase {

    // Timeout for all gRPC calls in milliseconds
    private static final Long TIMEOUT_IN_MS = 5000L;

    private final UserService userService;

    @Override
    public Mono<RegisterUserResponse> registerUser(Mono<RegisterUserRequest> request) {
        return request
            .flatMap(req -> userService.registerUser(UserMapper.toDomain(req))
                .map(user -> UserMapper.toRegisterUserResponse(user))
                .doOnSuccess(response -> log.info("User registered with ID: {}", response.getUser().getId()))
                .doOnError(this::handleError))
            .timeout(Duration.ofMillis(TIMEOUT_IN_MS));
    }


    @Override
    public Mono<DeleteUserResponse> deleteUser(Mono<DeleteUserRequest> request) {
        // TODO implement
        return super.deleteUser(request);
    }



    @Override
    public Mono<GetUserByIdResponse> getUserById(Mono<GetUserByIdRequest> request) {
        // TODO implement
        return super.getUserById(request);
    }



    @Override
    public Mono<LoginUserResponse> loginUser(Mono<LoginUserRequest> request) {
        // TODO implement
        return super.loginUser(request);
    }



    @Override
    public Mono<UpdateUserResponse> updateUser(Mono<UpdateUserRequest> request) {
        // TODO implement
        return super.updateUser(request);
    }

        
    private void handleError(Throwable error) {
        if (error instanceof MonoTimeoutException) {
            log.error("Operation timed out", error);
        } else {
            log.error("Error occurred", error);
        }
    }
}
