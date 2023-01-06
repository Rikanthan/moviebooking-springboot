package com.cineplex.moviebooking.service;

import com.cineplex.moviebooking.model.Film;
import com.cineplex.moviebooking.model.FilmDAO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmService {
    ResponseEntity<Film> addFilm(FilmDAO film);
    ResponseEntity<Film> getFilmById(long id);
    ResponseEntity<String> deleteFilmById(long id);
    ResponseEntity<Film> updateAvailableSeats(long id,int noOfSeats);
    ResponseEntity<Film> updateFilm(Film film);
    List<Film> showAllFilms();
    List<Film> showFilmsByName(String name);
    List<Film> todayShows();
    List<Film> showFilmsByDate(String date);

}
