package com.cineplex.moviebooking.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


public class FilmDAO {
    public Date getShowDateTime() {
		return showDateTime;
	}
	public void setShowDateTime(Date showDateTime) {
		this.showDateTime = showDateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	private String name;
    private String description;
    private Date showDateTime;
    private int availableSeats;
}
