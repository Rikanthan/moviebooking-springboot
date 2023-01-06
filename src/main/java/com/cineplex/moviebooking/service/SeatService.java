package com.cineplex.moviebooking.service;

import com.cineplex.moviebooking.model.Seat;
import com.cineplex.moviebooking.model.SeatKey;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SeatService {
    List<Seat> showSeatForSpecificShow(long showId);
    ResponseEntity<Seat> getSpecificSeat(SeatKey key);
    ResponseEntity<Seat> bookSeat(SeatKey key);
    ResponseEntity<Seat> cancelBooking(SeatKey key);
    ResponseEntity<Seat> addSeat(Seat seat);
    int availableSeats(long showId);
    ResponseEntity<String> deleteSeats(SeatKey key);

}
