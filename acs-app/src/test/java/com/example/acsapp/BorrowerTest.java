package com.example.acsapp;

import com.example.acsapp.domain.Borrower;
import com.example.acsapp.endpoint.request.BorrowerRequest;
import com.example.acsapp.repositories.BorrowerRepository;
import com.example.acsapp.service.IBorrowerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Tewaraj
 */
@SpringBootTest
@ActiveProfiles("dev")
public class BorrowerTest {

    private final IBorrowerService borrowerService;

    private final BorrowerRepository borrowerRepository;

    @Autowired
    public BorrowerTest(IBorrowerService borrowerService, BorrowerRepository borrowerRepository) {
        this.borrowerService = borrowerService;
        this.borrowerRepository = borrowerRepository;
    }

    @Test
    public void findAll() {
        borrowerService.findAll();
    }

    @Test
    public void registerBorrower() throws Exception {
        String message = borrowerService.registration(BorrowerRequest.Builder.instance()
                .withEmail("testing%^%@gmail.com")
                .withName("Testing Borrower")
                .build());
        assertNotNull(message);
        Optional<Borrower> borrower = borrowerRepository.findByEmail("testing%^%@gmail.com");
        borrower.ifPresent(borrowerRepository::delete);
    }

}
