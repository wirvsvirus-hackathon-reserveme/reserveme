package com.reserveme.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String name;

    // Geo information
    private String street;
    private String houseNumber;
    private String postcode;
    private String city;

    // Shop details
    private Integer reservedCapacity;
    private Integer appointmentCapacity;
    private Duration appointmentForerun;
    private Duration appointmentDuration;
}
