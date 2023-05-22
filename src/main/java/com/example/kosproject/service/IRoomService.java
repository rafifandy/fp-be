package com.example.kosproject.service;

import com.example.kosproject.model.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoomService {
    Room create(Room room) throws Exception;
    Page<Room> findAll(Integer page, Integer size, String direction, String sortBy);
    Room findById(Integer id) throws Exception;
    List<Room> findByRoomNumber(String startNumber, String endNumber) throws Exception;
    List<Room> findByRoomType(String roomType) throws Exception;
    void updateById(Room room, Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
}
