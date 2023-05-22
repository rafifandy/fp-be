package com.example.kosproject.repository;

import com.example.kosproject.model.entity.Tenancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TenancyRepository extends JpaRepository<Tenancy, Integer>, PagingAndSortingRepository<Tenancy, Integer> {

    @Query("SELECT t FROM Tenancy t WHERE t.roomPrice.room.roomNumber = :roomNumber")
    Optional<Tenancy> findByRoomNumber(String roomNumber) throws Exception;

//    @Query("SELECT t FROM Tenancy t WHERE t.startDate = :startDate")
    List<Tenancy> findByStartDate(Date startDate);

    @Query("SELECT t FROM Tenancy t WHERE t.endDate = :endDate")
    List<Tenancy> findByEndDate(Date endDate);
}
