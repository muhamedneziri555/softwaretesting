package com.example.javaPharma.service;

import com.example.javaPharma.pojo.dto.CreateDateCreatedRequest;
import com.example.javaPharma.pojo.dto.CreateExpiryDateRequest;
import com.example.javaPharma.pojo.entity.ExpiryDate;

import java.util.List;

public interface ExpiryDateService {
    List<ExpiryDate> getAllExpiryDates();

    ExpiryDate getExpiryDateById(Long id);

    ExpiryDate saveExpiryDate(CreateExpiryDateRequest expiryDate);

    void deleteExpiryDate(Long id);
}