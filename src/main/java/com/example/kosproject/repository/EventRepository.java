package com.example.kosproject.repository;

import com.example.kosproject.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>, PagingAndSortingRepository<Event, Integer> {

    @Query("SELECT e FROM Event e WHERE e.eventId = :id")
    Event findByEventId(Integer id);

    @Query("SELECT e FROM Event e WHERE e.eventName LIKE %?1%")
    List<Event> findByEventNameContains(String eventName);

    @Query("SELECT e FROM Event e WHERE e.eventDate = :date")
    List<Event> findByEventDate(Date date);
}
