package com.cineplex.moviebooking.controller;

import com.cineplex.moviebooking.model.Seat;
import com.cineplex.moviebooking.model.SeatKey;
import com.cineplex.moviebooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping(value = "/api/v1/seat")
@RestController

public class SeatController {
    @Autowired
    private SeatService service;

    @GetMapping
    public List<Seat> showSeatForSpecificShow(@RequestParam long showId) {
        return service.showSeatForSpecificShow(showId);
    }
    @GetMapping("/specificSeat")
    public ResponseEntity<Seat> getSpecificSeat(@RequestParam int showId,@RequestParam int seatNo) {
        return service.getSpecificSeat(new SeatKey(showId,seatNo));
    }
    @PutMapping("/booking")
    public ResponseEntity<Seat> bookSeat(@RequestParam int showId,@RequestParam int seatNo) {
        return service.bookSeat(new SeatKey(showId,seatNo));
    }
    @PutMapping("/cancelbooking")
    public ResponseEntity<Seat> cancelBooking(@RequestParam int showId,@RequestParam int seatNo) {
        return service.cancelBooking(new SeatKey(showId,seatNo));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteSeats(@RequestParam int showId,@RequestParam int seatNo) {
        return service.deleteSeats(new SeatKey(showId,seatNo));
    }
}
