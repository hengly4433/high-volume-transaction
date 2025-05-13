package com.blockcode.high_valume_transaction_service.service;

import java.time.LocalDateTime;

import javax.naming.ServiceUnavailableException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blockcode.high_valume_transaction_service.dto.TransactionDTO;
import com.blockcode.high_valume_transaction_service.entity.Transaction;
import com.blockcode.high_valume_transaction_service.entity.User;
import com.blockcode.high_valume_transaction_service.exception.ResourceNotFoundException;
import com.blockcode.high_valume_transaction_service.repository.TransactionRepository;
import com.blockcode.high_valume_transaction_service.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository txRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    @CircuitBreaker(name = "transactionService", fallbackMethod = "fallbackCreate")
    public TransactionDTO create(TransactionDTO dto) {
        User user = userRepo.findById(dto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", dto.getUserId()));

        Transaction tx = new Transaction(null, dto.getType(), dto.getAmount(), LocalDateTime.now(), user);
        Transaction saved = txRepo.save(tx);
        return map(saved);
    }

    public TransactionDTO fallbackCreate(TransactionDTO dto, Throwable ex) throws ServiceUnavailableException {
         throw new ServiceUnavailableException(
            "Transaction service is currently unavailable. Please try again later."
        );
    }

    @Override
    public Page<TransactionDTO> listByUser(Long userId, int page, int size) {
        return txRepo.findByUserId(userId, PageRequest.of(page, size))
                    .map(this::map);
    }

    @Override
    public TransactionDTO get(Long id) {
        Transaction tx = txRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", id));
        return map(tx);
    }

    private TransactionDTO map(Transaction tx) {
        return new TransactionDTO(tx.getId(), tx.getType(), tx.getAmount(),
                                  tx.getTimestamp(), tx.getUser().getId());
    }

}
