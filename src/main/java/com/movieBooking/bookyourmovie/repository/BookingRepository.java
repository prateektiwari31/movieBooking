package com.movieBooking.bookyourmovie.repository;

import com.movieBooking.bookyourmovie.model.Booking;
import com.movieBooking.bookyourmovie.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository< Booking , Long > {
    List<Booking> findByUserId(Long userid);
    List<Booking> findByShowId(Long showid);
}
