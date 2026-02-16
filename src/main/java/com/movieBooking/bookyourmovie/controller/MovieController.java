package com.movieBooking.bookyourmovie.controller;

import com.movieBooking.bookyourmovie.DTO.MovieDTO;
import com.movieBooking.bookyourmovie.model.Movie;
import com.movieBooking.bookyourmovie.service.MovieService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addmovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(movieService.addMovie(movieDTO), HttpStatus.OK);
    }

    @GetMapping("/getallmovies")
    public ResponseEntity<List<Movie>> getAllMovies()
    {
        Object movieDTO;
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/getmoviesbygenre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre)
    {
        Object movieDTO;
        return new ResponseEntity<>(movieService.getMoviesByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("/getmoviesbylanguage")
    public ResponseEntity<List<Movie>> getMoviesByLanguage(@RequestParam String language)
    {
        Object movieDTO;
        return new ResponseEntity<>(movieService.getMoviesByLanguage(language), HttpStatus.OK);
    }

    @GetMapping("/getmoviesbytitle")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@RequestParam String title)
    {
        Object movieDTO;
        return new ResponseEntity<>(movieService.getMoviesByTitle(title), HttpStatus.OK);
    }

    @PutMapping("/updatemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO)
    {
        return new ResponseEntity<>(movieService.updateMovie(id,movieDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deletemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id)
    {
        return new ResponseEntity<>(movieService.deleteMovie(id), HttpStatus.OK);
    }








}
