package com.cineplex.moviebooking.repository;

import com.cineplex.moviebooking.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
    @Override
    Optional<Film> findById(Long aLong);

    Optional<List<Film>> findByName(String name);
}
