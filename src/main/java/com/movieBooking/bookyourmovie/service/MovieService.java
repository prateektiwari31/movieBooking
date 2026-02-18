package com.movieBooking.bookyourmovie.service;

import com.movieBooking.bookyourmovie.DTO.MovieDTO;
import com.movieBooking.bookyourmovie.model.Movie;
import com.movieBooking.bookyourmovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie=new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setDuration(movieDTO.getDuration());
        movie.setGenre(movieDTO.getGenre());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(String genre) {
        Optional<List<Movie>> listMovie=movieRepository.findByGenre(genre);
        if(listMovie.isPresent())
            return listMovie.get();
        else throw new RuntimeException("No movie found for genre "+ genre);
    }

    public List<Movie> getMoviesByLanguage(String language) {
        Optional<List<Movie>> listMovie=movieRepository.findByLanguage(language);
        if(listMovie.isPresent())
            return listMovie.get();
        else throw new RuntimeException("No movie found for language "+ language);
    }

    public List<Movie> getMoviesByTitle(String title) {
        Optional<List<Movie>> listMovie=movieRepository.findByName(title);
        if(listMovie.isPresent())
            return listMovie.get();
        else throw new RuntimeException("No movie found for title "+ title);
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id)
                         .orElseThrow(()-> new RuntimeException("No Movie Found for the id "+id));
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setDuration(movieDTO.getDuration());
        movie.setGenre(movieDTO.getGenre());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
