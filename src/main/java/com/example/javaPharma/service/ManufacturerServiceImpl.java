package com.example.javaPharma.service;


import com.example.javaPharma.pojo.dto.CreateManufacturerRequest;
import com.example.javaPharma.pojo.entity.Category;
import com.example.javaPharma.pojo.entity.Manufacturer;
import com.example.javaPharma.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturerById(Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }

    @Override
    public Manufacturer saveManufacturer(CreateManufacturerRequest manufacturer) {
        Manufacturer man = new Manufacturer();
        man.setName(manufacturer.getName());
        return manufacturerRepository.save(man);
    }

    @Override
    public void deleteManufacturer(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
