package com.example.kosproject.repository;

import com.example.kosproject.model.entity.EventTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EventTenantRepository extends JpaRepository<EventTenant, Integer>, PagingAndSortingRepository<EventTenant, Integer> {

    @Query("SELECT et FROM EventTenant et WHERE et.event.eventName = :event_name")
    List<EventTenant> findByEventName(String event_name);

    @Query("SELECT et FROM EventTenant et WHERE et.user.userId = :tenant_id")
    List<EventTenant> findByTenantId(Integer tenant_id);
}
