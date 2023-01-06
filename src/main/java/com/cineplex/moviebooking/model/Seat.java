package com.cineplex.moviebooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @EmbeddedId
    private SeatKey key;

    @Column(name = "status")
    private boolean status;

    public Seat(SeatKey key, boolean status) {
        this.key = key;
        this.status = status;
    }
}
