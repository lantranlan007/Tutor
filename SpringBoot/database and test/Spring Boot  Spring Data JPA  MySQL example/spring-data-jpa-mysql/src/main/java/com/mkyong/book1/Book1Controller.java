package com.mkyong.book1;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book1s")
public class Book1Controller {

    @Autowired
    private Book1Service book1Service;

    @GetMapping
    public List<Book1> findAll() {
        return book1Service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book1> findById(@PathVariable Long id) {
        return book1Service.findById(id);
    }

    // create a book
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public Book1 create(@RequestBody Book1 book) {
        return book1Service.save(book);
    }

    // update a book
    @PutMapping
    public Book1 update(@RequestBody Book1 book) {
        return book1Service.save(book);
    }

    // delete a book
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        book1Service.deleteById(id);
    }

    @GetMapping("/find/title/{title}")
    public List<Book1> findByTitle(@PathVariable String title) {
        return book1Service.findByTitle(title);
    }

    @GetMapping("/find/date-after/{date}")
    public List<Book1> findByPublishedDateAfter(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return book1Service.findByPublishedDateAfter(date);
    }

}

