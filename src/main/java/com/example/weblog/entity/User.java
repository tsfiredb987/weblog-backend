package com.example.weblog.entity;

import javax.persistence.*;
import lombok.Data;

// 使用者實體，映射 users 表
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}