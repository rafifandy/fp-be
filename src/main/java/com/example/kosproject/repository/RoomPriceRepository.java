package com.example.kosproject.repository;

import com.example.kosproject.model.entity.RoomPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomPriceRepository extends JpaRepository<RoomPrice, Integer>, PagingAndSortingRepository<RoomPrice, Integer> {
    @Query("SELECT rp FROM RoomPrice rp WHERE rp.room.roomNumber = :roomNumber")
    List<RoomPrice> findByRoom(String roomNumber) throws Exception;

    @Query("SELECT rp FROM RoomPrice rp WHERE rp.price BETWEEN :startPrice AND :endPrice")
    List<RoomPrice> findByPrice(Integer startPrice, Integer endPrice) throws Exception;
}
