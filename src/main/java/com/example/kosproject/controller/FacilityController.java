package com.example.kosproject.controller;

import com.example.kosproject.model.entity.Facility;
import com.example.kosproject.model.request.FacilityRequest;
import com.example.kosproject.model.response.PagingResponse;
import com.example.kosproject.model.response.SuccessResponse;
import com.example.kosproject.service.FacilityService;
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
@RequestMapping("/facility")
@Validated
public class FacilityController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FacilityService facilityService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody @Valid FacilityRequest facilityRequest) throws Exception {
        Facility facility = modelMapper.map(facilityRequest, Facility.class);
        Facility result = facilityService.create(facility);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add facility", result));
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "facilityId") String sortBy
    ) throws Exception {
        Page<Facility> facilities = facilityService.findAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get facility list", facilities));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) throws Exception {
        Facility facility = facilityService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get facility by id", facility));
    }

    @GetMapping("/name/{facilityName}")
    public ResponseEntity getByName(@PathVariable("facilityName") String facilityName) throws Exception {
        List<Facility> facilities = facilityService.findByFacilityName(facilityName);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get facility by name", facilities));
    }

    @GetMapping("/desc/{facilityDescription}")
    public ResponseEntity getByDescription(@PathVariable("facilityDescription") String facilityDescription) throws Exception {
        List<Facility> facilities = facilityService.findByFacilityDescription(facilityDescription);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get facility by description", facilities));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody FacilityRequest facilityRequest, @PathVariable("id") Integer id) throws Exception {
        Facility facility = modelMapper.map(facilityRequest, Facility.class);
        facilityService.upateById(facility, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success update facility", facility));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception {
        facilityService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete facility", id));
    }
}
