package com.hotel.hotel_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvalidToken {
    @Id
    private String id;
    private Date expiryTime;

}
