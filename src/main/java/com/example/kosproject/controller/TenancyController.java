package com.example.kosproject.controller;

import com.example.kosproject.model.entity.RoomPrice;
import com.example.kosproject.model.entity.Tenancy;
import com.example.kosproject.model.request.TenancyRequest;
import com.example.kosproject.model.response.PagingResponse;
import com.example.kosproject.model.response.SuccessResponse;
import com.example.kosproject.service.RoomPriceService;
import com.example.kosproject.service.TenancyService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenancy")
@Validated
public class TenancyController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TenancyService tenancyService;

    @Autowired
    private RoomPriceService roomPriceService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody TenancyRequest tenancyRequest) throws Exception {
        Tenancy tenancy = modelMapper.map(tenancyRequest, Tenancy.class);
        tenancy.setRoomPrice(roomPriceService.findById(tenancyRequest.getPriceId()));
        Tenancy result = tenancyService.create(tenancy);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add tenancy", result));
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "tenancyId") String sortBy
    ) throws Exception {
        Page<Tenancy> tenancies = tenancyService.findAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get tenancy list", tenancies));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) throws Exception {
        Tenancy tenancy = tenancyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get tenancy by id", tenancy));
    }

    @GetMapping("/room-number/{roomNumber}")
    public ResponseEntity getByRoomNumber(@PathVariable("roomNumber") String roomNumber) throws Exception {
        Optional<Tenancy> tenancy = tenancyService.findByRoomNumber(roomNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get tenancy by roomNumber", tenancy));
    }

    @GetMapping("/start-date")
    public ResponseEntity getByStartDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) throws Exception {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = format.parse(startDate);
//        System.out.println(date);
//        tenancyService.findByStartDate(date);
        List<Tenancy> tenancies = tenancyService.findByStartDate(startDate);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get tenancy by startDate", tenancies));
    }

    @GetMapping("/end-date")
    public ResponseEntity getByEndDate(@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws Exception {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Tenancy> tenancies = tenancyService.findByEndDate(endDate);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get tenancy by endDate", tenancies));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Tenancy tenancy, @PathVariable("id") Integer id) throws Exception {
//        Tenancy tenancy = modelMapper.map(tenancyRequest, Tenancy.class);
        tenancyService.updateById(tenancy, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success update tenancy", tenancy));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception {
        tenancyService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete tenancy", id));
    }
}
