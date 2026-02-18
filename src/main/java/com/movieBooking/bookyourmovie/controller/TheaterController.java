package com.movieBooking.bookyourmovie.controller;


import com.movieBooking.bookyourmovie.DTO.TheaterDTO;
import com.movieBooking.bookyourmovie.model.Theater;
import com.movieBooking.bookyourmovie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addtheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO)
    {
       return new ResponseEntity<>(theaterService.addTheater(theaterDTO), HttpStatus.OK);
    }

    @GetMapping("/gettheaterbylocation")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location)
    {
        return new ResponseEntity<>(theaterService.getTheaterByLocation(location),HttpStatus.OK);
    }

    @PutMapping("/updatetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@RequestParam Long id, @RequestBody TheaterDTO theaterDTO)
    {
         return new ResponseEntity<>(theaterService.updateTheater(id,theaterDTO),HttpStatus.OK);
    }

    @DeleteMapping("/deletetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id)
    {
        theaterService.deleteTheater(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
