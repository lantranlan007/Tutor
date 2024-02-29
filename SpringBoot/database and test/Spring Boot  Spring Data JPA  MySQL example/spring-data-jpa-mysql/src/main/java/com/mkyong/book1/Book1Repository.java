package com.mkyong.book1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface Book1Repository extends JpaRepository<Book1, Long> {

    List<Book1> findByTitle(String title);

    // Custom query
    @Query("SELECT b FROM Book b WHERE b.publishDate > :date")
    List<Book1> findByPublishedDateAfter(@Param("date") LocalDate date);

}