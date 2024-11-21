package com.ng.NgBank.repository;

import com.ng.NgBank.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscationHistoryRepository extends JpaRepository<TransactionHistory,Integer> {
}
