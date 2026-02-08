package com.example.demo.entity;

import com.example.demo.base.BaseEntity;
import com.example.demo.enums.UserStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status ;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "is_active")
    private Boolean isActive;
}
