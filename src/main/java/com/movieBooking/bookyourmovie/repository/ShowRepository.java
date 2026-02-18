package com.movieBooking.bookyourmovie.repository;

import com.movieBooking.bookyourmovie.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
    Optional<List<Show>> findByMovieId(Long id);

    Optional<List<Show>> findByTheaterId(Long id);
}
