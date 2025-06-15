package com.example.javaPharma.service;

import com.example.javaPharma.pojo.dto.CreateManufacturerRequest;
import com.example.javaPharma.pojo.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> getAllManufacturers();

    Manufacturer getManufacturerById(Long id);

    Manufacturer saveManufacturer(CreateManufacturerRequest manufacturer);

    void deleteManufacturer(Long id);
}
