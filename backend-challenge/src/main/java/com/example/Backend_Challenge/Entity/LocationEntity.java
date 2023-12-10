package com.example.backend_challenge.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "location")
@Setter
@Getter
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String locationName;

    @Column(nullable = false, unique = true)
    private String locationOnMap;

    @OneToMany(mappedBy = "eventLocation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","eventLocation"})
    private Set<EventEntity> events;



}
