package com.example.javaPharma.service;


import com.example.javaPharma.pojo.dto.CreateDateCreatedRequest;
import com.example.javaPharma.pojo.entity.Category;
import com.example.javaPharma.pojo.entity.DateCreated;
import com.example.javaPharma.repository.DateCreatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateCreatedServiceImpl implements DateCreatedService {

    @Autowired
    private DateCreatedRepository dateCreatedRepository;

    @Override
    public List<DateCreated> getAllDateCreatedEntries() {
        return dateCreatedRepository.findAll();
    }

    @Override
    public DateCreated getDateCreatedById(Long id) {
        return dateCreatedRepository.findById(id).orElse(null);
    }

    @Override
    public DateCreated saveDateCreated(CreateDateCreatedRequest dateCreated) {
        DateCreated dat = new DateCreated();
        dat.setDate(dateCreated.getDate());
        return dateCreatedRepository.save(dat);
    }

    @Override
    public void deleteDateCreated(Long id) {
        dateCreatedRepository.deleteById(id);
    }

    @Override
    public List<DateCreated> getAllDateCreated() {
        return null;
    }
}