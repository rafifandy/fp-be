package com.example.kosproject.repository;

import com.example.kosproject.model.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Integer>, PagingAndSortingRepository<Facility, Integer> {

    List<Facility> findByFacilityNameContains(String name) throws Exception;

    List<Facility> findByFacilityDescriptionContains(String description) throws Exception;
}
