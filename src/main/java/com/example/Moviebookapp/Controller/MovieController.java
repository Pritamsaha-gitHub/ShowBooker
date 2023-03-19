package com.example.Moviebookapp.Controller;

import com.example.Moviebookapp.EntryDTOs.MovieEntryDTO;
import com.example.Moviebookapp.ResponseDTOs.MovieResponse;
import com.example.Moviebookapp.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody() MovieEntryDTO movieEntryDTO){
        try {
            String result=movieService.addMovie(movieEntryDTO);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result="Movie could not be added";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_language")
    public ResponseEntity<List<MovieResponse>> getByLanguage(@RequestParam("language") String language){
        List<MovieResponse>movieResponseList=movieService.getByLanguage(language);
        return new ResponseEntity<>(movieResponseList,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get_by_genre")
    public ResponseEntity<List<MovieResponse>> getByGenre(@RequestParam("genre") String genre){
        List<MovieResponse>movieResponseList=movieService.getByGenre(genre);
        return new ResponseEntity<>(movieResponseList,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get_by_name")
    public ResponseEntity<MovieResponse> getByName(@RequestParam("movie_name") String movie_name){
        try {
            MovieResponse movieResponse=movieService.getByName(movie_name);
            return new ResponseEntity<>(movieResponse,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FOUND);
        }
    }
}
