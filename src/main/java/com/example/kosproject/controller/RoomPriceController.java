package com.example.kosproject.controller;

import com.example.kosproject.model.entity.RoomPrice;
import com.example.kosproject.model.request.RoomPriceRequest;
import com.example.kosproject.model.response.PagingResponse;
import com.example.kosproject.model.response.SuccessResponse;
import com.example.kosproject.service.RoomPriceService;
import com.example.kosproject.service.RoomService;
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
@RequestMapping("/room-price")
@Validated
public class RoomPriceController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoomPriceService roomPriceService;

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody RoomPriceRequest roomPriceRequest) throws Exception {
        RoomPrice roomPrice = modelMapper.map(roomPriceRequest, RoomPrice.class);
        roomPrice.setRoom(roomService.findById(roomPriceRequest.getRoomId()));
        RoomPrice result = roomPriceService.create(roomPrice);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add roomPrice", result));
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "priceId") String sortBy
    ) throws Exception {
        Page<RoomPrice> roomPrices = roomPriceService.findAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get room price list", roomPrices));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) throws Exception {
        RoomPrice roomPrice = roomPriceService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get room price by id", roomPrice));
    }

    @GetMapping("/number/{roomNumber}")
    public ResponseEntity getByRoom(@PathVariable("roomNumber") String roomNumber) throws Exception {
        List<RoomPrice> roomPrices = roomPriceService.findByRoom(roomNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get room price by roomNumber", roomPrices));
    }

    @GetMapping("/price")
    public ResponseEntity getByPrice(@RequestParam Integer startPrice, @RequestParam Integer endPrice) throws Exception {
        List<RoomPrice> roomPrices = roomPriceService.findByPrice(startPrice, endPrice);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get room price by price", roomPrices));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody RoomPriceRequest roomPriceRequest, @PathVariable("id") Integer id) throws Exception {
        RoomPrice roomPrice = modelMapper.map(roomPriceRequest, RoomPrice.class);
        roomPriceService.updateById(roomPrice, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success update room price", roomPrice));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception {
        roomPriceService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete room price", id));
    }
}
