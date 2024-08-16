package com.example.LibraConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendNewBookNotification(String email, String bookTitle, String bookDescription) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Nouveau Livre Disponible : " + bookTitle);
        message.setText("Bonjour,\n\nNous avons ajouté un nouveau livre : " + bookTitle + "\n\nDescription : " + bookDescription + "\n\nCordialement,\nL'équipe LibraConnect");
        emailSender.send(message);
    }
}
