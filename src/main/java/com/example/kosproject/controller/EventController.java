package com.example.kosproject.controller;

import com.example.kosproject.model.entity.Event;
import com.example.kosproject.model.request.EventRequest;
import com.example.kosproject.model.response.PagingResponse;
import com.example.kosproject.model.response.SuccessResponse;
import com.example.kosproject.service.EventService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/event")
@Validated
public class EventController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody @Valid EventRequest eventRequest) throws Exception {
        Event event = new Event();
        event.setEventName(eventRequest.getEventName());
        event.setEventDescription(eventRequest.getEventDescription());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventDate(format.parse(eventRequest.getEventDate()));

        Event result = eventService.create(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add event", result));
    }

    @GetMapping
    public ResponseEntity getAllEvent(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "eventId") String sortBy
    ) throws Exception {
        Page<Event> events = eventService.findAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get event list", events));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) throws Exception {
        Event event = eventService.findByEventId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get event by id", event));
    }

    @GetMapping("/name/{eventName}")
    public ResponseEntity getByName(@PathVariable("eventName") String eventName) throws Exception {
        List<Event> events = eventService.findByEventName(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get event by Name", events));
    }

    @GetMapping("/date/{eventDate}")
    public ResponseEntity getByDate(@PathVariable("eventDate") String eventDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Event> events = eventService.findByEventDate(format.parse(eventDate));
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get event by Date", events));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody EventRequest eventRequest, @PathVariable("id") Integer id) throws Exception {
        Event event = new Event();
        event.setEventName(eventRequest.getEventName());
        event.setEventDescription(eventRequest.getEventDescription());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventDate(format.parse(eventRequest.getEventDate()));

        eventService.updateById(event, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success update event", event));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception {
        eventService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete event", id));
    }
}
