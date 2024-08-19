package com.weuoimi.user_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users")  // This is the name of the table in PostgreSQL
public class UserDomain {

    @Id
    @Column("id")  // Column name in the table
    private UUID id;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    // TODO: Add methods if needed for domain logic
    // For example, methods for updating fields, validation, etc.
}

