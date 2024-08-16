package com.example.LibraConnect.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraConnect.model.Book;
import com.example.LibraConnect.model.Borrow;
import com.example.LibraConnect.model.User;
import com.example.LibraConnect.repository.BookRepository;
import com.example.LibraConnect.repository.BorrowRepository;
import com.example.LibraConnect.repository.UserRepository;
import com.example.LibraConnect.service.BorrowService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserRepository userRepository; 
    
    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/borrow")
    public ResponseEntity<Borrow> borrowBook(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long bookId = request.get("bookId");

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (userOpt.isPresent() && bookOpt.isPresent()) {
            Borrow borrow = new Borrow();
            borrow.setUser(userOpt.get());
            borrow.setBook(bookOpt.get());
            borrow.setBorrowDate(LocalDate.now());
            borrow.setReturnDate(LocalDate.now().plusWeeks(2));
            borrow.setStatus("IN_PROGRESS");

            Borrow savedBorrow = borrowRepository.save(borrow);
            return ResponseEntity.ok(savedBorrow);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Borrow>> getUserBorrows(@PathVariable Long userId) {
        List<Borrow> borrows = borrowRepository.findByUserId(userId);
        return ResponseEntity.ok(borrows);
    }
    @GetMapping("/all")
    public List<Borrow> getAllBorrow() {
        return borrowRepository.findAll();
    }
    @GetMapping("/count")
    public long getBorrowCount() {
    	return borrowService.getBorrowCount();
    }
    
    @PutMapping("/return/{id}")
    public ResponseEntity<Borrow> returnBook(@PathVariable Long id) {
        Optional<Borrow> borrowOpt = borrowRepository.findById(id);
        
        if (borrowOpt.isPresent()) {
            Borrow borrow = borrowOpt.get();
            borrow.setStatus("RETURNED");
            borrowRepository.save(borrow);
            return ResponseEntity.ok(borrow);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
  
}
