package com.cineplex.moviebooking.repository;

import com.cineplex.moviebooking.model.Seat;
import com.cineplex.moviebooking.model.SeatKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatKey> {
    @Override
    Optional<Seat> findById(SeatKey seatKey);

    Optional<Seat> findByKey(SeatKey seatKey);
}
