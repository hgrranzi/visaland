package com.hgrranzi.visaland.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "application")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private VisaCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consul_id")
    private Consul consul;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppStatus status;

    @Column(name = "comment")
    private String comment;

}
