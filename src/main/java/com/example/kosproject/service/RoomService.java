package com.example.kosproject.service;

import com.example.kosproject.exception.EntityExistException;
import com.example.kosproject.exception.NotFoundException;
import com.example.kosproject.model.entity.Room;
import com.example.kosproject.repository.RoomRepository;
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
public class RoomService implements IRoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room create(Room room) throws Exception {
        try {
            return roomRepository.save(room);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Room> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Room> rooms = roomRepository.findAll(pageable);
        return rooms;
    }

    @Override
    public Room findById(Integer id) throws Exception {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()){
            throw new NotFoundException("Room not found");
        }
        return room.get();
    }

    @Override
    public List<Room> findByRoomNumber(String startNumber, String endNumber) throws Exception {
        List<Room> rooms = roomRepository.findByRoomNumber(startNumber, endNumber);
        if (rooms.isEmpty()) {
            throw new Exception("Room not found");
        }
        return rooms;
    }

    @Override
    public List<Room> findByRoomType(String roomType) throws Exception {
        List<Room> rooms = roomRepository.findByRoomTypeContains(roomType);
        if (rooms.isEmpty()) {
            throw new Exception("Room not found");
        }
        return rooms;
    }

    @Override
    public void updateById(Room room, Integer id) throws Exception {
        try {
            Room existRoom = findById(id);
            room.setRoomId(existRoom.getRoomId());
            roomRepository.save(room);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            Room existRoom = findById(id);
            roomRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
