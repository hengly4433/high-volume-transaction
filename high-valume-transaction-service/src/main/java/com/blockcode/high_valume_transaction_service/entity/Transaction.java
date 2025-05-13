package com.blockcode.high_valume_transaction_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id @GeneratedValue
    private Long           id;         // now a nullable wrapper type
    private String         type;
    private BigDecimal     amount;     // added field
    private LocalDateTime  timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    private User           user;
}
