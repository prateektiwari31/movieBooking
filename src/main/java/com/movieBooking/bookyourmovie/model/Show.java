package com.movieBooking.bookyourmovie.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Show {

    private Long id;
    private LocalDateTime showTime;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id" , nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theater_id" , nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<Booking> bookings;
}
