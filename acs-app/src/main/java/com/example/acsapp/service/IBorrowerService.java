package com.example.acsapp.service;

import com.example.acsapp.domain.Borrower;
import com.example.acsapp.endpoint.request.BorrowerRequest;

import java.util.List;

/**
 * @author Tewaraj
 */
public interface IBorrowerService {

    String registration(BorrowerRequest request) throws Exception;

    List<Borrower> findAll();
}
