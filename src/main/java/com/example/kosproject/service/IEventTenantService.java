package com.example.kosproject.service;

import com.example.kosproject.model.entity.EventTenant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEventTenantService {
    EventTenant create(EventTenant eventTenant) throws Exception;
    Page<EventTenant> findAll(Integer page, Integer size, String direction, String sortBy);
    EventTenant findById(Integer id) throws Exception;
    List<EventTenant> findByTenantId(Integer tenantId) throws Exception;
    List<EventTenant> findByEventName(String eventName) throws Exception;
    void updateById(EventTenant eventTenant, Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
}
