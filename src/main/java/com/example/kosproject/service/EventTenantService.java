package com.example.kosproject.service;

import com.example.kosproject.exception.EntityExistException;
import com.example.kosproject.exception.NotFoundException;
import com.example.kosproject.model.entity.EventTenant;
import com.example.kosproject.repository.EventTenantRepository;
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
public class EventTenantService implements IEventTenantService{

    @Autowired
    private EventTenantRepository eventTenantRepository;

    @Override
    public EventTenant create(EventTenant eventTenant) throws Exception {
        try {
            return eventTenantRepository.save(eventTenant);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<EventTenant> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page -1), size, sort);
        Page<EventTenant> eventTenants = eventTenantRepository.findAll(pageable);
        return eventTenants;
    }

    @Override
    public EventTenant findById(Integer id) throws Exception {
        Optional<EventTenant> eventTenant = eventTenantRepository.findById(id);
        if (eventTenant.isEmpty()) {
            throw new NotFoundException("Event-Tenant not found");
        }
        return eventTenant.get();
    }

    @Override
    public List<EventTenant> findByTenantId(Integer tenantId) throws Exception {
        List<EventTenant> eventTenants = eventTenantRepository.findByTenantId(tenantId);
        if (eventTenants.isEmpty()) {
            throw new NotFoundException("Event-Tenant not found");
        }
        return eventTenants;
    }

    @Override
    public List<EventTenant> findByEventName(String eventName) throws Exception {
        List<EventTenant> eventTenants = eventTenantRepository.findByEventName(eventName);
        if (eventTenants.isEmpty()){
            throw new NotFoundException("Event-Tenant not found");
        }
        return eventTenants;
    }

    @Override
    public void updateById(EventTenant eventTenant, Integer id) throws Exception {
        try {
            EventTenant existEventTenant = findById(id);
            eventTenant.setEventTenantId(existEventTenant.getEventTenantId());
            eventTenantRepository.save(eventTenant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            EventTenant existEventTenant = findById(id);
            eventTenantRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
