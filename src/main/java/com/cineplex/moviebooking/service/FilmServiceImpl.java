package com.cineplex.moviebooking.service;

import com.cineplex.moviebooking.model.Film;
import com.cineplex.moviebooking.model.FilmDAO;
import com.cineplex.moviebooking.model.Seat;
import com.cineplex.moviebooking.model.SeatKey;
import com.cineplex.moviebooking.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository repository;

    @Autowired
    private SeatService service;
    @Override
    public ResponseEntity<Film> addFilm(FilmDAO film) {
        Seat seat = new Seat();
        Film newFilm = new Film();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try{
            date = format.parse(film.getShowDateTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        newFilm.setAvailableSeats(film.getAvailableSeats());
        newFilm.setDescription(film.getDescription());
        newFilm.setName(film.getName());
        newFilm.setShowDateTime(date);
        newFilm = repository.save(newFilm);
        for(int i=1; i <= film.getAvailableSeats(); i++){
            SeatKey key = new SeatKey(newFilm.getId(),i);
            seat.setKey(key);
            service.addSeat(seat);
        }
        return new ResponseEntity<Film>(newFilm, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Film> getFilmById(long id) {
        Optional<Film> film = repository.findById(id);
        return film.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Film(), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<String> deleteFilmById(long id) {
        Optional<Film> film = repository.findById(id);
        String msg;
        if(film.isPresent()){
            repository.deleteById(id);
            msg = "Film deleted successfully";
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        }else {
            msg = "can't found the film";
            return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Film> updateAvailableSeats(long id, int noOfSeats) {
        Optional<Film> film = repository.findById(id);
        if(film.isPresent()){
            repository.deleteById(id);
            Film updatedFilm = film.get();
            updatedFilm.setAvailableSeats(noOfSeats);
            updatedFilm.setId(id);
            return new ResponseEntity<Film>(updatedFilm,HttpStatus.OK);
        }else {
            return new ResponseEntity<Film>(new Film(),HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Film> updateFilm(Film film) {
        Optional<Film> filmDetails = repository.findById(film.getId());
        if(filmDetails.isPresent()){
            int existingSeats = filmDetails.get().getAvailableSeats();
            int updatedSeats = film.getAvailableSeats();
            Seat seat = new Seat();
            if(existingSeats < updatedSeats){// have to increase seat count
                for(int i = existingSeats+1; i <= updatedSeats; i++){
                    SeatKey key = new SeatKey(film.getId(),i);
                    seat.setKey(key);
                    service.addSeat(seat);
                }
            } else if (existingSeats > updatedSeats) { // have to remove seats
                for(int i = updatedSeats + 1; i <= existingSeats; i++){
                    SeatKey key = new SeatKey(film.getId(),i);
                    service.deleteSeats(key);
                }
            }
            Film newFilm = repository.save(film);
            return new ResponseEntity<Film>(newFilm,HttpStatus.OK);
        }else {
            return new ResponseEntity<Film>(new Film(),HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Film> showAllFilms() {
        return repository.findAll();
    }

    @Override
    public List<Film> showFilmsByName(String name) {
        List<Film> films = new ArrayList<>();
        for(Film film: repository.findAll()){
            if(film.getName().toLowerCase().contains(name.toLowerCase())){
                films.add(film);
            }
        }
        return films;
    }

    @Override
    public List<Film> todayShows() {
        LocalDate date = LocalDate.now();
        return showFilmsByDate(date.toString());
    }

    @Override
    public List<Film> showFilmsByDate(String date) {
        List<Film> shows = new ArrayList<>();
        for(Film film: showAllFilms()){
            if(film.getShowDateTime().toString().contains(date)){
                shows.add(film);
            }
        }
        return shows;
    }
}
