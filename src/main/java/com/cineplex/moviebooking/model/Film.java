package com.cineplex.moviebooking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Film")

public class Film {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
   
	private String name;
    private String description;
    private Date showDateTime;
    private int availableSeats;
	
    public long getId() {
		return id;
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
	public Date getShowDateTime() {
		return showDateTime;
	}
	public void setShowDateTime(Date showDateTime) {
		this.showDateTime = showDateTime;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", description=" + description + ", showDateTime=" + showDateTime
				+ ", availableSeats=" + availableSeats + "]";
	}
	 public void setId(long id) {
			this.id = id;
		}
	


}
