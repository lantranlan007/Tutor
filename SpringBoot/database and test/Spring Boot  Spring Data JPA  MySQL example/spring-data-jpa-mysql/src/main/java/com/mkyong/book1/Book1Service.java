package com.mkyong.book1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Book1Service {

    @Autowired
    private Book1Repository book1Repository;

    public List<Book1> findAll() {
        return book1Repository.findAll();
    }

    public Optional<Book1> findById(Long id) {
        return book1Repository.findById(id);
    }

    public Book1 save(Book1 book) {
        return book1Repository.save(book);
    }

    public void deleteById(Long id) {
        book1Repository.deleteById(id);
    }

    public List<Book1> findByTitle(String title) {
        return book1Repository.findByTitle(title);
    }

    public List<Book1> findByPublishedDateAfter(LocalDate date) {
        return book1Repository.findByPublishedDateAfter(date);
    }
}
