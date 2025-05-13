package com.blockcode.high_valume_transaction_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blockcode.high_valume_transaction_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
