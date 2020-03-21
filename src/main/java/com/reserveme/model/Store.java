package com.reserveme.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.util.UUID;

@Entity
@Data
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

    public Store(String name, String street, String houseNumber, String postcode, String city, Integer reservedCapacity, Integer appointmentCapacity, Duration appointmentDuration, Duration appointmentForerun) {
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
        this.reservedCapacity = reservedCapacity;
        this.appointmentCapacity = appointmentCapacity;
        this.appointmentDuration = appointmentDuration;
        this.appointmentForerun = appointmentForerun;
    }
}
