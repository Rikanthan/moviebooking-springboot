package com.cineplex.moviebooking.controller;

import com.cineplex.moviebooking.model.Film;
import com.cineplex.moviebooking.model.FilmDAO;
import com.cineplex.moviebooking.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value = "/api/v1/film")
@RestController
@CrossOrigin
public class FilmController  {
    @Autowired
    private FilmService service;
    @PostMapping
    public ResponseEntity<Film> addFilm( @RequestBody FilmDAO film) {
        return service.addFilm(film);
    }

    @GetMapping("/id")
    public ResponseEntity<Film> getFilmById(long id) {
        return service.getFilmById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFilmById(long id) {
        return service.deleteFilmById(id);
    }

    @PutMapping("/changeAvailableSeats")
    public ResponseEntity<Film> updateAvailableSeats(@RequestParam long id,@RequestParam int noOfSeats) {
        return service.updateAvailableSeats(id,noOfSeats);
    }

    @PutMapping
    public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
        return service.updateFilm(film);
    }

    @GetMapping
    public List<Film> showAllFilms() {
        return service.showAllFilms();
    }

    @GetMapping("/name")
    public List<Film> showFilmsByName(@RequestParam String name) {
        return service.showFilmsByName(name);
    }

    @GetMapping("/todayFilm")
    public List<Film> todayShows() {
        return service.todayShows();
    }

    @GetMapping("/date")
    public List<Film> showFilmsByDate(@RequestParam String date) {
        return service.showFilmsByDate(date);
    }
}
