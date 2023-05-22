package com.example.kosproject.repository;

import com.example.kosproject.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer>, PagingAndSortingRepository<Payment, Integer> {
}
