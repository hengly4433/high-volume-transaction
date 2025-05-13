package com.blockcode.high_valume_transaction_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long           id;
    private String         type;
    private BigDecimal     amount;
    private LocalDateTime  timestamp;
    private Long           userId;
}
