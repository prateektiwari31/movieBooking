package com.movieBooking.bookyourmovie.repository;

import com.movieBooking.bookyourmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {

}
