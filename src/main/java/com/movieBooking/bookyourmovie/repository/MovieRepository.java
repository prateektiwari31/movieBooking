package com.movieBooking.bookyourmovie.repository;

import com.movieBooking.bookyourmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    Optional<List<Movie>> findByGenre(String genre);

    Optional<List<Movie>> findByLanguage(String language);

    Optional<List<Movie>> findByName(String title);
}
