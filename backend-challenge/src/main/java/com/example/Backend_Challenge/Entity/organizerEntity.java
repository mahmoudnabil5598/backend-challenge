package com.example.backend_challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "organizer")
@Setter
@Getter
public class OrganizerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;


    @Column(nullable = false, unique = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String organizerName;


    @Column(nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String organizerDescription;

    @Column(nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String organizerImage;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizer")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","organizer"})
    private Set<EventEntity> events;

    @PrePersist
    private void prePersist() {
        if (organizerImage == null) {
            organizerImage = "uploads/defaultOrganizerImage.png";
        }
    }
}
