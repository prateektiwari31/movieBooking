package com.movieBooking.bookyourmovie.service;


import com.movieBooking.bookyourmovie.DTO.ShowDTO;
import com.movieBooking.bookyourmovie.model.Booking;
import com.movieBooking.bookyourmovie.model.Movie;
import com.movieBooking.bookyourmovie.model.Show;
import com.movieBooking.bookyourmovie.model.Theater;
import com.movieBooking.bookyourmovie.repository.MovieRepository;
import com.movieBooking.bookyourmovie.repository.ShowRepository;
import com.movieBooking.bookyourmovie.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository  theaterRepository;

    public Show createShow(ShowDTO showDTO) {
        Movie movie=movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("No Movie Present For this show"));

        Theater theater=theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(()-> new RuntimeException("No theater For this show"));
        Show show = new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        return  showRepository.save(show);

    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public List<Show> getShowsByMovie(Long id) {
        Optional<List<Show>> lshow = showRepository.findByMovieId(id);

        if(lshow.isPresent())
            return lshow.get();
        else throw new RuntimeException("Movie not found ");
    }

    public List<Show> getShowsByTheater(Long id) {
        Optional<List<Show>> lshow=showRepository.findByTheaterId(id);

        if(lshow.isPresent())
            return lshow.get();
        else throw new RuntimeException("Movie not found");
    }

    public Show updateShow(Long id, ShowDTO showDTO) {

        Movie movie=movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("No Movie Present For this show"));

        Theater theater=theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(()-> new RuntimeException("No theater For this show"));

        Show show=showRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Show Nopt Found For id"+id));

        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);


        return  showRepository.save(show);
    }

    public void deleteShow(Long id) {

        if(!showRepository.existsById(id))
            throw new RuntimeException("No Show Is Available For Id"+id);

        List<Booking> bookings=showRepository.findById(id).get().getBookings();

        if(!bookings.isEmpty())
            throw new RuntimeException("Show can not be delete with existing bookings");

        showRepository.deleteById(id);
    }
}
