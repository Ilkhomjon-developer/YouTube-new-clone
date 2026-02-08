package com.example.demo.entity;

import com.example.demo.base.BaseLongEntity;
import com.example.demo.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class UserRoleEntity extends BaseLongEntity {

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoleEnum role;


}
