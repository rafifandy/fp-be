package com.example.kosproject.controller;

import com.example.kosproject.model.entity.EventTenant;
import com.example.kosproject.model.request.EventTenantRequest;
import com.example.kosproject.model.response.PagingResponse;
import com.example.kosproject.model.response.SuccessResponse;
import com.example.kosproject.service.EventTenantService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-tenant")
@Validated
public class EventTenantController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventTenantService eventTenantService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody @Valid EventTenant eventTenant) throws Exception {
//        EventTenant eventTenant = modelMapper.map(eventTenantRequest, EventTenant.class);
        EventTenant result = eventTenantService.create(eventTenant);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add event-tenant", result));
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "eventTenantId") String sortBy
    ) throws Exception {
        Page<EventTenant> eventTenants = eventTenantService.findAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get event-tenant list", eventTenants));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) throws Exception {
        EventTenant eventTenant = eventTenantService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get event-tenant by id", eventTenant));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity getByTenant(@PathVariable("tenantId") Integer tenantId) throws Exception {
        List<EventTenant> eventTenants = eventTenantService.findByTenantId(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get event-tenant by tenantId", eventTenants));
    }

    @GetMapping("/event/{eventName}")
    public ResponseEntity getByEvent(@PathVariable("eventName") String eventName) throws Exception {
        List<EventTenant> eventTenants = eventTenantService.findByEventName(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get event-tenant by eventName", eventTenants));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody EventTenant eventTenant, @PathVariable("id") Integer id) throws Exception {
//        EventTenant eventTenant = modelMapper.map(eventTenantRequest, EventTenant.class);
        eventTenantService.updateById(eventTenant, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success update event-tenant", eventTenant));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception {
        eventTenantService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete event-tenant", id));
    }
}
