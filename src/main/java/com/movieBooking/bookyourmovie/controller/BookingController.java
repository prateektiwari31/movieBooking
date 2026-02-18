package com.movieBooking.bookyourmovie.controller;


import com.movieBooking.bookyourmovie.DTO.BookingDTO;
import com.movieBooking.bookyourmovie.model.Booking;
import com.movieBooking.bookyourmovie.model.BookingStatus;
import com.movieBooking.bookyourmovie.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO)
    {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getuserbookings/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userid)
    {
        return ResponseEntity.ok(bookingService.getUserBookings(userid));
    }

    @GetMapping("/getshowbookings/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookingService.getShowBookings(id));
    }
    @PutMapping("{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }
    @PutMapping("{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

    @GetMapping("/getbookingsbystatus/{bookingStatus}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus bookingStatus)
    {
        return ResponseEntity.ok(bookingService.getBookingsByStatus(bookingStatus));
    }
}
