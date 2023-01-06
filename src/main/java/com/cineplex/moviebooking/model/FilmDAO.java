package com.cineplex.moviebooking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmDAO {
    private String name;
    private String description;
    private String showDateTime;
    private int availableSeats;
}
