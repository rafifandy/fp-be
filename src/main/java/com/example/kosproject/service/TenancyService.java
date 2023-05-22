package com.example.kosproject.service;

import com.example.kosproject.exception.EntityExistException;
import com.example.kosproject.exception.NotFoundException;
import com.example.kosproject.model.entity.Tenancy;
import com.example.kosproject.repository.TenancyRepository;
import com.example.kosproject.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TenancyService implements ITenancyService{

    @Autowired
    private TenancyRepository tenancyRepository;

    @Override
    public Tenancy create(Tenancy tenancy) throws Exception {
        try {
            return tenancyRepository.save(tenancy);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Tenancy> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Tenancy> tenancies = tenancyRepository.findAll(pageable);
        return tenancies;
    }

    @Override
    public Tenancy findById(Integer id) throws Exception {
        Optional<Tenancy> tenancy = tenancyRepository.findById(id);
        if (tenancy.isEmpty()) {
            throw new NotFoundException("Tenancy not found");
        }
        return tenancy.get();
    }

    @Override
    public Optional<Tenancy> findByRoomNumber(String roomNUmber) throws Exception {
        Optional<Tenancy> tenancy = tenancyRepository.findByRoomNumber(roomNUmber);
        if (tenancy.isEmpty()) {
            throw new NotFoundException("Tenancy not found");
        }
        return tenancy;
    }

    @Override
    public List<Tenancy> findByStartDate(Date startDate) throws Exception {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Tenancy> tenancies = tenancyRepository.findByStartDate(startDate);
        if (tenancies.isEmpty()) {
            System.out.println(startDate);
            throw new Exception("Gagal service");
        }
        return tenancies;
    }

    @Override
    public List<Tenancy> findByEndDate(Date endDate) throws Exception {
        List<Tenancy> tenancies = tenancyRepository.findByEndDate(endDate);

        if (tenancies.isEmpty()) {
            throw new Exception();
        }
        return tenancies;
    }

    @Override
    public void updateById(Tenancy tenancy, Integer id) throws Exception {
        try {
            Tenancy existTenancy = findById(id);
            tenancy.setTenancyId(existTenancy.getTenancyId());
            tenancyRepository.save(tenancy);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            Tenancy existTenancy = findById(id);
            tenancyRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
