package com.movieBooking.bookyourmovie.repository;

import com.movieBooking.bookyourmovie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
