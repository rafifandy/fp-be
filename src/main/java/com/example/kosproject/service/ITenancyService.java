package com.example.kosproject.service;

import com.example.kosproject.model.entity.Tenancy;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITenancyService {
    Tenancy create(Tenancy tenancy) throws Exception;
    Page<Tenancy> findAll(Integer page, Integer size, String direction, String sortBy);
    Tenancy findById(Integer id) throws Exception;
    Optional<Tenancy> findByRoomNumber(String roomNUmber) throws Exception;
    List<Tenancy> findByStartDate(Date startDate) throws Exception;
    List<Tenancy> findByEndDate(Date endDate) throws Exception;
    void updateById(Tenancy tenancy, Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
}
