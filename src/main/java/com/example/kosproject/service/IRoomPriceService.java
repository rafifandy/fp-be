package com.example.kosproject.service;

import com.example.kosproject.model.entity.RoomPrice;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface IRoomPriceService {
    RoomPrice create(RoomPrice roomPrice) throws Exception;
    Page<RoomPrice> findAll(Integer page, Integer size, String direction, String sortBy);
    RoomPrice findById(Integer id) throws Exception;
    List<RoomPrice> findByRoom(String roomNumber) throws Exception;
    List<RoomPrice> findByPrice(Integer startPrice, Integer endPrice) throws Exception;
    void updateById(RoomPrice roomPrice, Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
}
