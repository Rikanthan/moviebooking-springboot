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

public class Seat {
    public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeatKey getKey() {
		return key;
	}

	public void setKey(SeatKey key) {
		this.key = key;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@EmbeddedId
    private SeatKey key;

    @Column(name = "status")
    private boolean status;

    public Seat(SeatKey key, boolean status) {
        this.key = key;
        this.status = status;
    }
}
