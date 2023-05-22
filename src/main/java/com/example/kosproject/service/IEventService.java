package com.example.kosproject.service;

import com.example.kosproject.model.entity.Event;
import com.example.kosproject.model.request.EventRequest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface IEventService {
    Event create(Event event) throws Exception;
    Page<Event> findAll(Integer page, Integer size, String direction, String sortBy);
    Event findByEventId(Integer id) throws Exception;
    List<Event> findByEventName(String eventName) throws Exception;
    List<Event> findByEventDate(Date EventDate) throws Exception;
    void updateById(Event event, Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
}
