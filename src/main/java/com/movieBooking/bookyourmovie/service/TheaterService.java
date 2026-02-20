package com.movieBooking.bookyourmovie.service;

import com.movieBooking.bookyourmovie.DTO.TheaterDTO;
import com.movieBooking.bookyourmovie.model.Theater;
import com.movieBooking.bookyourmovie.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public Theater addTheater(TheaterDTO theaterDTO) {
        Theater theater=new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());

        return theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location) {
       Optional<List<Theater>> listTheater= theaterRepository.findByTheaterLocation(location);

       if(listTheater.isPresent())
           return listTheater.get();
       else throw new RuntimeException("No Theater Found For Location :"+location);
    }

    public Theater updateTheater(Long id, TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No Theater Is Found For Id :"+id));

        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());

        return theaterRepository.save(theater);
    }

    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}
