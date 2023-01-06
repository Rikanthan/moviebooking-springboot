package com.cineplex.moviebooking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Film")
@ToString
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String description;
    private Date showDateTime;
    private int availableSeats;

}
