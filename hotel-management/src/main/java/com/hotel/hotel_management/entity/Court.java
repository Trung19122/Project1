package com.hotel.hotel_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "sport_type_id", nullable = false)
    private SportType sportType;

    @Column(nullable = false)
    private String name;

    private String courtType;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private CourtStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public enum CourtStatus {
        AVAILABLE,
        MAINTENANCE,
        CLOSED
    }
}
