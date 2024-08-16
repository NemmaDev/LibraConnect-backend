package com.example.LibraConnect.service;

import com.example.LibraConnect.model.Book;
import com.example.LibraConnect.model.Subscriber;
import com.example.LibraConnect.repository.BookRepository;
import com.example.LibraConnect.repository.SubscriberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private SubscriberRepository subscriberRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        // Sauvegarder le livre dans la base de données
        Book savedBook = bookRepository.save(book);
        
        // Envoyer une notification par e-mail à tous les abonnés
       List<Subscriber> subscribers = subscriberRepository.findAll();
        for (Subscriber subscriber : subscribers) {
            emailService.sendNewBookNotification(
                subscriber.getEmail(),
                savedBook.getTitle(),
                savedBook.getDescription()
            );
        }
        
        return savedBook;
    }

    public long getBookCount() {
        return bookRepository.count();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setDescription(updatedBook.getDescription());
            book.setImage(updatedBook.getImage());
            book.setCategory(updatedBook.getCategory());
            // Mettez à jour les autres champs si nécessaire
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found");
        }
    }
}
