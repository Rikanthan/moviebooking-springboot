package com.cineplex.moviebooking.service;

import com.cineplex.moviebooking.model.Seat;
import com.cineplex.moviebooking.model.SeatKey;
import com.cineplex.moviebooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository repository;
    @Override
    public List<Seat> showSeatForSpecificShow(long showId) {
        List<Seat> seats = new ArrayList<>();
        for(Seat seat: repository.findAll()){
            if(seat.getKey().getShowId() == showId){
                seats.add(seat);
            }
        }
        return seats;
    }

    @Override
    public ResponseEntity<Seat> getSpecificSeat(SeatKey key) {
        Optional<Seat> seat = repository.findByKey(key);
        return seat.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Seat(), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Seat> bookSeat(SeatKey key) {
       Optional<Seat> seat = repository.findById(key);
       Seat bookedSeat = new Seat();
       if(seat.isPresent()){
           bookedSeat = seat.get();
           bookedSeat.setStatus(true);
           return new ResponseEntity<Seat>(repository.save(bookedSeat),HttpStatus.OK);
       }else {
           return new ResponseEntity<Seat>(new Seat(),HttpStatus.NOT_FOUND);
       }
    }

    @Override
    public ResponseEntity<Seat> cancelBooking(SeatKey key) {
        Optional<Seat> seat = repository.findById(key);
        Seat bookedSeat = new Seat();
        if(seat.isPresent()){
            bookedSeat = seat.get();
            bookedSeat.setStatus(false);
            return new ResponseEntity<Seat>(repository.save(bookedSeat),HttpStatus.OK);
        }else {
            return new ResponseEntity<Seat>(new Seat(),HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Seat> addSeat(Seat seat) {
        return new ResponseEntity<Seat>(repository.save(seat),HttpStatus.CREATED);
    }

    @Override
    public int availableSeats(long showId) {
        int count = 0;
        for(Seat seat: repository.findAll()){
            if(!seat.isStatus() && seat.getKey().getShowId() == showId){
                count++;
            }
        }
        return count;
    }

    @Override
    public ResponseEntity<String> deleteSeats(SeatKey key) {
        Optional<Seat> seat = repository.findByKey(key);
        if(seat.isPresent()){
            repository.deleteById(key);
            return new ResponseEntity<String>("Seat deleted successful",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Seatkey is wrong",HttpStatus.OK);
        }
    }
}
