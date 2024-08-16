package com.example.LibraConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraConnect.model.Borrow;
import com.example.LibraConnect.repository.BorrowRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    public List<Borrow> getBorrowsByUserId(Long userId) {
        return borrowRepository.findByUserId(userId);
    }

    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }
    
    public long getBorrowCount() {
    	return borrowRepository.count();
    }
    
    public void markAsReturned(Long borrowId) {
        Optional<Borrow> borrowOpt = borrowRepository.findById(borrowId);
        if (borrowOpt.isPresent()) {
            Borrow borrow = borrowOpt.get();
            borrow.setStatus("RETURNED");
            borrowRepository.save(borrow);
        }
    }
   
}
