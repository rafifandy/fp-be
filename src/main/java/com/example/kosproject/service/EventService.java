package com.example.kosproject.service;

import com.example.kosproject.exception.EntityExistException;
import com.example.kosproject.exception.NotFoundException;
import com.example.kosproject.model.entity.Event;
import com.example.kosproject.model.request.EventRequest;
import com.example.kosproject.repository.EventRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event create(Event event) throws Exception {
        try {
            return eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Event> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Event> events = eventRepository.findAll(pageable);
        return events;
    }

    @Override
    public Event findByEventId(Integer id) throws Exception {
        Event event = eventRepository.findByEventId(id);
        if (event == null) {
            throw new NotFoundException("Event not found");
        }
        return event;
    }

    @Override
    public List<Event> findByEventName(String eventName) throws Exception {
        List<Event> events = eventRepository.findByEventNameContains(eventName);
        if (events.isEmpty()) {
            throw new Exception("Event not found");
        }
        return events;
    }

    @Override
    public List<Event> findByEventDate(Date EventDate) throws Exception {
        List<Event> events = eventRepository.findByEventDate(EventDate);
        if (events.isEmpty()) {
            throw new Exception("Event not found");
        }
        return events;
    }

    @Override
    public void updateById(Event event, Integer id) throws Exception {
        try {
            Event existEvent = findByEventId(id);
            existEvent.setEventName(event.getEventName());
            existEvent.setEventDescription(event.getEventDescription());
            existEvent.setEventDate(event.getEventDate());
            eventRepository.save(existEvent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            Event existEvent = findByEventId(id);
            eventRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
