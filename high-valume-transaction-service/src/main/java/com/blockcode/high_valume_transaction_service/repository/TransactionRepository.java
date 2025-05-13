package com.blockcode.high_valume_transaction_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blockcode.high_valume_transaction_service.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @org.springframework.cache.annotation.Cacheable("transactionByUser")
    Page<Transaction> findByUserId(Long userId, Pageable pageable);
}
