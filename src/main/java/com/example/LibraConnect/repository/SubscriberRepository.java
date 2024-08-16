package com.example.LibraConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraConnect.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}
