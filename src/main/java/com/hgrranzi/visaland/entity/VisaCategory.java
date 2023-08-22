package com.hgrranzi.visaland.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "visa_category")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class VisaCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_duration_days", nullable = false)
    private int maxDurationDays;

}
