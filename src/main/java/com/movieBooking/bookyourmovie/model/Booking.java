package com.movieBooking.bookyourmovie.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {

    private Long id;
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;

    @ElementCollection(fetch = FetchType.EAGER)  // element collection create a new table in database
    @CollectionTable(name = "booking_seat_numbers") // with two column 1st is current class id and 2nd is field on which it applied
    private List<String> seatNumbers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id",nullable = false)
    private Show show;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}
