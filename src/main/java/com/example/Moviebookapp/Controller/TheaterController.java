package com.example.Moviebookapp.Controller;

import com.example.Moviebookapp.EntryDTOs.TheaterEntryDTO;
import com.example.Moviebookapp.Models.TheaterEntity;
import com.example.Moviebookapp.ResponseDTOs.TheaterResponse;
import com.example.Moviebookapp.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody() TheaterEntryDTO theaterEntryDTO){
        try {
            String result=theaterService.addTheater(theaterEntryDTO);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result="Theater could not added";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_location")
    public ResponseEntity<List<TheaterResponse>> getByLocation(@RequestParam ("location") String location){
        List<TheaterResponse> theaterEntityList=theaterService.getByLocation(location);
        return new ResponseEntity<>(theaterEntityList,HttpStatus.CREATED);
    }
}
