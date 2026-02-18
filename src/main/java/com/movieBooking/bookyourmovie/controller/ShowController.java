package com.movieBooking.bookyourmovie.controller;


import com.movieBooking.bookyourmovie.DTO.ShowDTO;
import com.movieBooking.bookyourmovie.model.Show;
import com.movieBooking.bookyourmovie.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;


    @PostMapping("/createshow")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO)
    {
       return new ResponseEntity<>(showService.createShow(showDTO), HttpStatus.OK);
    }

    @GetMapping("/getallshows")
    public ResponseEntity<List<Show>> getAllShows()
    {
        return new ResponseEntity<>(showService.getAllShows(),HttpStatus.OK);
    }

    @GetMapping("/getshowsbymovie/{id}")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long id)
    {
        return new ResponseEntity<>(showService.getShowsByMovie(id),HttpStatus.OK);
    }

    @GetMapping("/getshowsbytheater/{id}")
    public ResponseEntity<List<Show>> getShowsByTheater(@PathVariable Long id)
    {
        return new ResponseEntity<>(showService.getShowsByTheater(id),HttpStatus.OK);
    }

    @PutMapping("/updateshow/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO)
    {
        return new ResponseEntity<>(showService.updateShow(id, showDTO),HttpStatus.OK);
    }

    @DeleteMapping("/deleteshow/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id, @RequestBody ShowDTO showDTO)
    {
       showService.deleteShow(id);

       return new ResponseEntity<>(HttpStatus.OK);
    }



}
