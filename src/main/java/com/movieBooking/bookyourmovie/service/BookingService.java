package com.movieBooking.bookyourmovie.service;

import com.movieBooking.bookyourmovie.DTO.BookingDTO;
import com.movieBooking.bookyourmovie.model.Booking;
import com.movieBooking.bookyourmovie.model.BookingStatus;
import com.movieBooking.bookyourmovie.model.Show;
import com.movieBooking.bookyourmovie.model.User;
import com.movieBooking.bookyourmovie.repository.BookingRepository;
import com.movieBooking.bookyourmovie.repository.ShowRepository;
import com.movieBooking.bookyourmovie.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public Double calculateTotalPrice(Double price, Integer numberOfSeates)
    {
        return price*numberOfSeates;
    }
    public  void validateSeatNumbers(Long showId , List<String> seatNumbers)
    {
        Show show = showRepository.findById(showId).get();

        Set<String> occupideSeats= show.getBookings().stream()
                .filter(b->b.getBookingStatus()!=BookingStatus.CANCELLED)
                .flatMap(b->b.getSeatNumbers().stream())
                .collect(Collectors.toSet());

        List<String> duplicateSeats= seatNumbers.stream()
                .filter(occupideSeats::contains)
                .collect(Collectors.toList());

        if(!duplicateSeats.isEmpty())
        {
            throw new RuntimeException("Seats Are Already Booked");
        }

    }
    public boolean isvacentseat(Long showid, Integer numberOfSeates)
    {
        Show show= showRepository.findById(showid).get();
        int bookedSeats= show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();

        return (show.getTheater().getTheaterCapacity()-bookedSeats) >= numberOfSeates ;
    }

    public Booking createBooking(BookingDTO bookingDTO) {
                                                                        // show from bookingDTO // number of booking from bookingDTO ,// check number of seats non confirm or in confirm state
        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(()-> new RuntimeException("show not found"));

        if( !isvacentseat(show.getId(), bookingDTO.getNumberOfSeats()))
            throw new RuntimeException("Booking not possible go for low seats");

        if(bookingDTO.getSeatNumbers().size()!=bookingDTO.getNumberOfSeats())
            throw new RuntimeException("Number of seats and selected seat numbers size should be same");

        validateSeatNumbers(show.getId(),bookingDTO.getSeatNumbers());

        User user=userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not Found"));

        Booking booking=new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookingTime(LocalDateTime.now());
        booking.setPrice(calculateTotalPrice(show.getPrice(),bookingDTO.getNumberOfSeats()));
        booking.setShow(show);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumbers());

        return bookingRepository.save(booking);

    }

    public  List<Booking> getUserBookings(Long userid) {
         return   bookingRepository.findByUserId(userid);
    }

    public List<Booking> getShowBookings(Long showid) {
        return  bookingRepository.findByShowId(showid);
    }

    public  Booking confirmBooking(Long bookingid) {
        Booking booking = bookingRepository.findById(bookingid)
                .orElseThrow(()-> new RuntimeException("booking id is not found for bookingid"));

        if(booking.getBookingStatus() != BookingStatus.PENDING)
            throw new RuntimeException("Booking is not in pending state");

        // Ask For Payment
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    public  Booking cancelBooking(Long id) {
    }

    public List<Booking> getBookingsByStatus(BookingStatus bookingStatus) {
    }
}
