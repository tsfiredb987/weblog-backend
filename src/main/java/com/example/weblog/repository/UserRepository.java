package com.example.weblog.repository;

import com.example.weblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// 使用者資料庫操作介面
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}