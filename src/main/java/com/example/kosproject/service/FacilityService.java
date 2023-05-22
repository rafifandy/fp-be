package com.example.kosproject.service;

import com.example.kosproject.exception.EntityExistException;
import com.example.kosproject.exception.NotFoundException;
import com.example.kosproject.model.entity.Facility;
import com.example.kosproject.repository.FacilityRepository;
import com.example.kosproject.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService implements IFacilityService{

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public Facility create(Facility facility) throws Exception {
        try {
            return facilityRepository.save(facility);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Facility> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page -1), size, sort);
        Page<Facility> facilities = facilityRepository.findAll(pageable);
        return facilities;
    }

    @Override
    public Facility findById(Integer id) throws Exception {
        Optional<Facility> facility = facilityRepository.findById(id);
        if (facility.isEmpty()) {
            throw new NotFoundException("Facility not found");
        }
        return facility.get();
    }

    @Override
    public List<Facility> findByFacilityName(String facilityName) throws Exception {
        List<Facility> facilities = facilityRepository.findByFacilityNameContains(facilityName);
        if (facilities.isEmpty()) {
            throw new NotFoundException("Facility not found");
        }
        return facilities;
    }

    @Override
    public List<Facility> findByFacilityDescription(String facilityDescription) throws Exception {
        List<Facility> facilities = facilityRepository.findByFacilityDescriptionContains(facilityDescription);
        if (facilities.isEmpty()) {
            throw new NotFoundException("Facility not found");
        }
        return facilities;
    }

    @Override
    public void upateById(Facility facility, Integer id) throws Exception {
        try {
            Facility existFacility = findById(id);
            facility.setFacilityId(existFacility.getFacilityId());
            facilityRepository.save(facility);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            Facility existFacility = findById(id);
            facilityRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
