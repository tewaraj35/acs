package com.example.acsapp.endpoint;

import com.example.acsapp.domain.Borrower;
import com.example.acsapp.endpoint.request.BorrowerRequest;
import com.example.acsapp.service.IBorrowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tewaraj
 */
@RestController
public class BorrowerEndpoint implements Serializable {

    protected static final long serialVersionUID = 1L;

    private final IBorrowerService borrowService;

    @Autowired
    public BorrowerEndpoint(IBorrowerService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/borrower/registration")
    public String registration(@RequestBody BorrowerRequest request) throws Exception {
       return borrowService.registration(request);
    }

    @GetMapping("/borrowers")
    public List<Borrower> findAll() {
        return borrowService.findAll();
    }

}
