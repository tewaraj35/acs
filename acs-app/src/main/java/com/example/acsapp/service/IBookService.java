package com.example.acsapp.service;

import com.example.acsapp.domain.Book;
import com.example.acsapp.endpoint.request.BookRequest;
import com.example.acsapp.endpoint.request.BorrowBookRequest;

import java.util.List;
import java.util.UUID;

/**
 * @author Tewaraj
 */
public interface IBookService {

    String registration(BookRequest request) throws Exception;

    List<Book> findAll();

    String borrowBook(UUID bookId, BorrowBookRequest request) throws Exception;

    String returnBook(UUID bookId) throws Exception;
}
