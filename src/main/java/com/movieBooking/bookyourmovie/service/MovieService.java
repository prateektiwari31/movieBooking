package com.movieBooking.bookyourmovie.service;

import com.movieBooking.bookyourmovie.DTO.MovieDTO;
import com.movieBooking.bookyourmovie.model.Movie;
import com.movieBooking.bookyourmovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie=new Movie();
        movie
    }

    public List<Movie> getAllMovies() {
    }

    public List<Movie> getMoviesByGenre(String genre) {
    }

    public List<Movie> getMoviesByLanguage(String language) {
    }

    public List<Movie> getMoviesByTitle(String title) {
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
    }

    public Void deleteMovie(Long id) {
    }
}
