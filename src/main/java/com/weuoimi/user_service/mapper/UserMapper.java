package com.weuoimi.user_service.mapper;

import java.util.UUID;

import com.weuoimi.grpc.user.service.UserServiceProto.GetUserByIdResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.LoginUserRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.LoginUserResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.RegisterUserRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.RegisterUserResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.UpdateUserRequest;
import com.weuoimi.grpc.user.service.UserServiceProto.UpdateUserResponse;
import com.weuoimi.grpc.user.service.UserServiceProto.User;
import com.weuoimi.user_service.domain.UserDomain;

public final class UserMapper {
    private UserMapper() {
    }

    // Convert from RegisterUserRequest to domain model UserDomain
    public static UserDomain toDomain(RegisterUserRequest request) {
        return UserDomain.builder()
            .email(request.getEmail())
            .password(request.getPassword())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build();
    }

    // Convert from LoginUserRequest to domain model UserDomain
    public static UserDomain toDomain(LoginUserRequest request) {
        return UserDomain.builder()
            .email(request.getEmail())
            .password(request.getPassword())
            .build();
    }

    // Convert from UpdateUserRequest to domain model UserDomain
    public static UserDomain toDomain(UpdateUserRequest request) {
        return UserDomain.builder()
            .id(UUID.fromString(request.getId()))
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build();
    }

    // Convert domain model UserDomain to protobuf User
    public static User toGrpc(UserDomain user) {
        return User.newBuilder()
            .setId(user.getId().toString())
            .setEmail(user.getEmail())
            .setFirstName(user.getFirstName())
            .setLastName(user.getLastName())
            .build();
    }

    // Convert domain model UserDomain to RegisterUserResponse protobuf message
    public static RegisterUserResponse toRegisterUserResponse(UserDomain user) {
        return RegisterUserResponse.newBuilder()
            .setUser(toGrpc(user))
            .build();
    }

    // Convert domain model UserDomain to LoginUserResponse protobuf message
    public static LoginUserResponse toLoginUserResponse(UserDomain user) {
        return LoginUserResponse.newBuilder()
            .setUser(toGrpc(user))
            .build();
    }

    // Convert domain model UserDomain to GetUserByIdResponse protobuf message
    public static GetUserByIdResponse toGetUserByIdResponse(UserDomain user) {
        return GetUserByIdResponse.newBuilder()
            .setUser(toGrpc(user))
            .build();
    }

    // Convert domain model UserDomain to UpdateUserResponse protobuf message
    public static UpdateUserResponse toUpdateUserResponse(UserDomain user) {
        return UpdateUserResponse.newBuilder()
            .setUser(toGrpc(user))
            .build();
    }
}
