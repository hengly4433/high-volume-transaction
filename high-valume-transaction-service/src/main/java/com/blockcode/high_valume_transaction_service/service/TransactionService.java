package com.blockcode.high_valume_transaction_service.service;


import org.springframework.data.domain.Page;

import com.blockcode.high_valume_transaction_service.dto.TransactionDTO;

public interface TransactionService {
    TransactionDTO create(TransactionDTO dto);
    Page<TransactionDTO> listByUser(Long userId, int page, int size);
    TransactionDTO get(Long id);
}
