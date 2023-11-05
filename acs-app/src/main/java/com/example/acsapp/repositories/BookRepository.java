package com.example.acsapp.repositories;

import com.example.acsapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Tewaraj
 */
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findAllByIsbnNo(String isbnNo);

}
