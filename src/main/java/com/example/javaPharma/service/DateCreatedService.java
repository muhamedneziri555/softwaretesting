package com.example.javaPharma.service;

import com.example.javaPharma.pojo.dto.CreateDateCreatedRequest;
import com.example.javaPharma.pojo.entity.DateCreated;

import java.util.List;

public interface DateCreatedService {
    List<DateCreated> getAllDateCreatedEntries();

    DateCreated getDateCreatedById(Long id);

    DateCreated saveDateCreated(CreateDateCreatedRequest dateCreated);

    void deleteDateCreated(Long id);

    List<DateCreated> getAllDateCreated();
}
