package com.example.kosproject.controller;

import com.example.kosproject.model.entity.Room;
import com.example.kosproject.model.request.RoomRequest;
import com.example.kosproject.model.response.PagingResponse;
import com.example.kosproject.model.response.SuccessResponse;
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
@RequestMapping("/room")
@Validated
public class RoomController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody RoomRequest roomRequest) throws Exception {
        Room room = modelMapper.map(roomRequest, Room.class);
        Room result = roomService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add room", result));
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "roomId") String sortBy
    ) throws Exception {
        Page<Room> rooms = roomService.findAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get event-tenant list", rooms));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) throws Exception {
        Room room = roomService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get room by id", room));
    }

    @GetMapping("/room-number")
    public ResponseEntity getByRoomNumber(
            @RequestParam String startNumber,
            @RequestParam String endNumber
    ) throws Exception {
        List<Room> rooms = roomService.findByRoomNumber(startNumber, endNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get room by roomNumber", rooms));
    }

    @GetMapping("/room-type")
    public ResponseEntity getByRoomType(@RequestParam String roomType) throws Exception {
        List<Room> rooms = roomService.findByRoomType(roomType);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get room by roomType", rooms));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Room room, @PathVariable("id") Integer id) throws Exception {
//        Room room = modelMapper.map(roomRequest, Room.class);
        roomService.updateById(room, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success update room", room));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delet room", id));
    }
}
