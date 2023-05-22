package com.example.kosproject.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "facility")
@Getter @Setter
@ToString
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Integer facilityId;

    @Column(name = "facility_name", nullable = false)
    private String facilityName;

    @Column(name = "facility_description")
    private String facilityDescription;

    @ManyToMany(mappedBy = "facility")
    @JsonBackReference
    private Set<Room> rooms;
}
