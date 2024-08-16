package com.example.LibraConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LibraConnect.model.Subscriber;
import com.example.LibraConnect.repository.SubscriberRepository;

@RestController
@RequestMapping("/api/newsletter")
public class NewsletterController {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        return ResponseEntity.ok("Abonnement réussi !");
    }
}
