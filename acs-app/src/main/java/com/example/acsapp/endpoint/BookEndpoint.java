package com.example.acsapp.endpoint;

import com.example.acsapp.domain.Book;
import com.example.acsapp.endpoint.request.BookRequest;
import com.example.acsapp.endpoint.request.BorrowBookRequest;
import com.example.acsapp.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author Tewaraj
 */
@RestController
public class BookEndpoint implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BookEndpoint.class);

    private final IBookService bookService;

    @Autowired
    public BookEndpoint(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book/registration")
    public String registration(@RequestBody BookRequest request) throws Exception {
       return bookService.registration(request);
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PutMapping("/book/{bookId}/borrow")
    public String borrowBook(@PathVariable UUID bookId, @RequestBody BorrowBookRequest request) throws Exception {
        LOGGER.info("Received borrow book {} request.", bookId);
        return bookService.borrowBook(bookId, request);
    }

    @PutMapping("/book/{bookId}/return")
    public String returnBook(@PathVariable UUID bookId) throws Exception {
        LOGGER.info("Received return book {} request.", bookId);
        return bookService.returnBook(bookId);
    }

}
