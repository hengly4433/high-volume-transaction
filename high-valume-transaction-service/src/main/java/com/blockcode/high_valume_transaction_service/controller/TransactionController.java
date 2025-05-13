package com.blockcode.high_valume_transaction_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blockcode.high_valume_transaction_service.dto.TransactionDTO;
import com.blockcode.high_valume_transaction_service.service.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService txService;

    @PostMapping
    public ResponseEntity<TransactionDTO> create(@RequestBody @Valid TransactionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(txService.create(dto));
    }

    @GetMapping
    public Page<TransactionDTO> list(
        @RequestParam Long userId,
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size) {
        return txService.listByUser(userId, page, size);
    }

    @GetMapping("/{id}")
    public TransactionDTO get(@PathVariable Long id) {
        return txService.get(id);
    }
}
