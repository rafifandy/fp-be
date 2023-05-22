package com.example.kosproject.service;

import com.example.kosproject.exception.EntityExistException;
import com.example.kosproject.exception.NotFoundException;
import com.example.kosproject.model.entity.RoomPrice;
import com.example.kosproject.repository.RoomPriceRepository;

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
public class RoomPriceService implements IRoomPriceService{

    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Override
    public RoomPrice create(RoomPrice roomPrice) throws Exception {
        try {
            return roomPriceRepository.save(roomPrice);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<RoomPrice> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<RoomPrice> roomPrices = roomPriceRepository.findAll(pageable);
        return roomPrices;
    }

    @Override
    public RoomPrice findById(Integer id) throws Exception {
        Optional<RoomPrice> roomPrice = roomPriceRepository.findById(id);
        if (roomPrice.isEmpty()) {
            throw new NotFoundException("Room Price not found");
        }
        return  roomPrice.get();
    }

    @Override
    public List<RoomPrice> findByRoom(String roomNumber) throws Exception {
        List<RoomPrice> roomPrices = roomPriceRepository.findByRoom(roomNumber);
        if (roomPrices.isEmpty()) {
            throw new NotFoundException("Room Price not found");
        }
        return roomPrices;
    }

    @Override
    public List<RoomPrice> findByPrice(Integer startPrice, Integer endPrice) throws Exception {
        List<RoomPrice> roomPrices = roomPriceRepository.findByPrice(startPrice, endPrice);
        if (roomPrices.isEmpty()) {
            throw new NotFoundException("Room price not found");
        }
        return roomPrices;
    }

    @Override
    public void updateById(RoomPrice roomPrice, Integer id) throws Exception {
        try {
            RoomPrice existRoomPrice = findById(id);
            roomPrice.setPriceId(existRoomPrice.getPriceId());
            roomPriceRepository.save(roomPrice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            RoomPrice existRoomPrice = findById(id);
            roomPriceRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
