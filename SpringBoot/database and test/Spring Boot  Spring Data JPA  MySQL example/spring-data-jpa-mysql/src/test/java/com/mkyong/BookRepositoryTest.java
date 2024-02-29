package com.mkyong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.mkyong.book.Book;
import com.mkyong.book.BookRepository;

// Default, JPA tests data are transactional and roll back at the end of each test.
// @DataJpaTest
// Add below will disabled the roll back
// @Transactional(propagation = Propagation.NOT_SUPPORTED)

// Default is true, set showSql to false will disable the SQL queries logging
// @DataJpaTest(showSql = false)
//@DataJpaTest
//@SpringBootTest(classes = {BookRepository.class, BookService.class})
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest(classes = {ConfigTest.class},
//webEnvironment = WebEnvironment.RANDOM_PORT)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSave() {

        Book b1 = new Book("Book A",
                BigDecimal.valueOf(9.99),
                LocalDate.of(2023, 8, 31));

        //testEM.persistAndFlush(b1); the same
        bookRepository.save(b1);

        Long savedBookID = b1.getId();

        Book book = bookRepository.findById(savedBookID).orElseThrow();
        // Book book = testEM.find(Book.class, savedBookID);

        assertEquals(savedBookID, book.getId());
        assertEquals("Book A", book.getTitle());
        assertEquals(BigDecimal.valueOf(9.99), book.getPrice());
        assertEquals(LocalDate.of(2023, 8, 31), book.getPublishDate());


    }

    @Test
    public void testUpdate() {

        Book b1 = new Book("Book A",
                BigDecimal.valueOf(9.99),
                LocalDate.of(2023, 8, 31));

        //testEM.persistAndFlush(b1);
        bookRepository.save(b1);

        // update price from 9.99 to 19.99
        b1.setPrice(BigDecimal.valueOf(19.99));

        // update
        bookRepository.save(b1);

        List<Book> result = bookRepository.findByTitle("Book A");

        assertEquals(1, result.size());

        Book book = result.get(0);
        assertNotNull(book.getId());
        assertTrue(book.getId() > 0);

        assertEquals("Book A", book.getTitle());
        assertEquals(BigDecimal.valueOf(19.99), book.getPrice());
        assertEquals(LocalDate.of(2023, 8, 31), book.getPublishDate());


    }

    @Test
    public void testFindByTitle() {

        Book b1 = new Book("Book A",
                BigDecimal.valueOf(9.99),
                LocalDate.of(2023, 8, 31));
        bookRepository.save(b1);

        List<Book> result = bookRepository.findByTitle("Book A");

        assertEquals(1, result.size());
        Book book = result.get(0);
        assertNotNull(book.getId());
        assertTrue(book.getId() > 0);

        assertEquals("Book A", book.getTitle());
        assertEquals(BigDecimal.valueOf(9.99), book.getPrice());
        assertEquals(LocalDate.of(2023, 8, 31), book.getPublishDate());

    }

    @Test
    public void testFindAll() {

        Book b1 = new Book("Book A",
                BigDecimal.valueOf(9.99),
                LocalDate.of(2023, 8, 31));
        Book b2 = new Book("Book B",
                BigDecimal.valueOf(19.99),
                LocalDate.of(2023, 7, 31));
        Book b3 = new Book("Book C",
                BigDecimal.valueOf(29.99),
                LocalDate.of(2023, 6, 10));
        Book b4 = new Book("Book D",
                BigDecimal.valueOf(39.99),
                LocalDate.of(2023, 5, 5));

        bookRepository.saveAll(List.of(b1, b2, b3, b4));

        List<Book> result = bookRepository.findAll();
        assertEquals(4, result.size());

    }

    @Test
    public void testFindByPublishedDateAfter() {

        Book b1 = new Book("Book A",
                BigDecimal.valueOf(9.99),
                LocalDate.of(2023, 8, 31));
        Book b2 = new Book("Book B",
                BigDecimal.valueOf(19.99),
                LocalDate.of(2023, 7, 31));
        Book b3 = new Book("Book C",
                BigDecimal.valueOf(29.99),
                LocalDate.of(2023, 6, 10));
        Book b4 = new Book("Book D",
                BigDecimal.valueOf(39.99),
                LocalDate.of(2023, 5, 5));

        bookRepository.saveAll(List.of(b1, b2, b3, b4));

        List<Book> result = bookRepository.findByPublishedDateAfter(
                LocalDate.of(2023, 7, 1));
        // b1 and b2
        assertEquals(2, result.size());

    }

    @Test
    public void testDeleteById() {

        Book b1 = new Book("Book A",
                BigDecimal.valueOf(9.99),
                LocalDate.of(2023, 8, 31));
        bookRepository.save(b1);

        Long savedBookID = b1.getId();

        // Book book = bookRepository.findById(savedBookID).orElseThrow();
        // Book book = testEM.find(Book.class, savedBookID);

        bookRepository.deleteById(savedBookID);

        Optional<Book> result = bookRepository.findById(savedBookID);
        assertTrue(result.isEmpty());

    }

}