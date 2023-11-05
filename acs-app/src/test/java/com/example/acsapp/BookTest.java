package com.example.acsapp;

import com.example.acsapp.domain.Book;
import com.example.acsapp.domain.Borrower;
import com.example.acsapp.domain.enums.BookStatus;
import com.example.acsapp.endpoint.request.BookRequest;
import com.example.acsapp.endpoint.request.BorrowBookRequest;
import com.example.acsapp.repositories.BookRepository;
import com.example.acsapp.repositories.BorrowerRepository;
import com.example.acsapp.service.IBookService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Tewaraj
 */
@SpringBootTest
@ActiveProfiles("dev")
public class BookTest {

    private final IBookService bookService;

    private final BookRepository bookRepository;

    private final BorrowerRepository borrowerRepository;

    @Autowired
    public BookTest(IBookService bookService,
                    BookRepository bookRepository,
                    BorrowerRepository borrowerRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Test
    public void findAll() {
        bookService.findAll();
    }

    @Test
    public void registerBook() throws Exception {
        String message = bookService.registration(BookRequest.Builder.instance()
                .withIsbnNo("9780596520680")
                .withAuthor("TEST")
                .withTitle("TEST")
                .build());
        assertNotNull(message);
        List<Book> books = bookRepository.findAllByIsbnNo("9780596520680");
        if (CollectionUtils.isNotEmpty(books)) {
            bookRepository.deleteAll(books);
        }
    }

    @Test
    public void borrowBook() throws Exception {
        Borrower borrower = borrowerRepository.save(Borrower.Builder.instance()
                .withId(UUID.randomUUID())
                .withName("TESTER")
                .withEmail("TESTER")
                .build());

        Book book = bookRepository.save(Book.Builder.instance()
                .withId(UUID.randomUUID())
                .withIsbnNo("9780596520680")
                .withAuthor("TEST")
                .withTitle("TEST")
                .withBorrower(borrower)
                .withStatus(BookStatus.AVAILABLE)
                .build());

        String message = bookService.borrowBook(book.getId(), BorrowBookRequest.Builder.instance()
                .withBorrowerId(borrower.getId()).build());

        assertNotNull(message);

        bookRepository.delete(book);
        borrowerRepository.delete(borrower);
    }

    @Test
    public void returnBook() throws Exception {
        Borrower borrower = borrowerRepository.save(Borrower.Builder.instance()
                .withId(UUID.randomUUID())
                .withName("TESTER")
                .withEmail("TESTER")
                .build());

        Book book = bookRepository.save(Book.Builder.instance()
                .withId(UUID.randomUUID())
                .withIsbnNo("9780596520680")
                .withAuthor("TEST")
                .withTitle("TEST")
                .withBorrower(borrower)
                .withStatus(BookStatus.AVAILABLE)
                .build());

        String messageBorrowed = bookService.borrowBook(book.getId(), BorrowBookRequest.Builder.instance()
                .withBorrowerId(borrower.getId()).build());

        assertNotNull(messageBorrowed);

        String messageReturned = bookService.returnBook(book.getId());

        assertNotNull(messageReturned);

        bookRepository.delete(book);
        borrowerRepository.delete(borrower);
    }
}
