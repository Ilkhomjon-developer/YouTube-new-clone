package com.example.demo.entity;

import com.example.demo.base.BaseLongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "email")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailEntity extends BaseLongEntity {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "attempts")
    private Integer attempts = 0;

    @Column(name = "message")
    private String message;

}
