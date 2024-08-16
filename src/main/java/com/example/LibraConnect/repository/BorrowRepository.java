package com.example.LibraConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraConnect.model.Borrow;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserId(Long userId);
}
