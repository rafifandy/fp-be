package com.example.kosproject.repository;

import com.example.kosproject.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer>, PagingAndSortingRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.roomNumber BETWEEN :startNumber AND :endNumber")
    List<Room> findByRoomNumber(String startNumber, String endNumber) throws Exception;

    List<Room> findByRoomTypeContains(String roomType) throws Exception;
}
