package com.example.kosproject.service;

import com.example.kosproject.model.entity.Facility;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFacilityService {
    Facility create(Facility facility) throws Exception;
    Page<Facility> findAll(Integer page, Integer size, String direction, String sortBy);
    Facility findById(Integer id) throws Exception;
    List<Facility> findByFacilityName(String facilityName) throws Exception;
    List<Facility> findByFacilityDescription(String facilityDescription) throws Exception;
    void upateById(Facility facility, Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
}
