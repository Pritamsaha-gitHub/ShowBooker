package com.example.Moviebookapp.Service;

import com.example.Moviebookapp.Converters.MovieConverters;
import com.example.Moviebookapp.EntryDTOs.MovieEntryDTO;
import com.example.Moviebookapp.Models.MovieEntity;
import com.example.Moviebookapp.Repository.MovieRepository;
import com.example.Moviebookapp.ResponseDTOs.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDTO movieEntryDTO) throws Exception{
        MovieEntity movieEntity= MovieConverters.convertDtoToEntity(movieEntryDTO);
        movieRepository.save(movieEntity);
        return "Movie added Sucessfully";
    }

    public List<MovieResponse> getByLanguage(String language){
        List<MovieEntity> movieEntityList=movieRepository.findBylanguage(language);
        List<MovieResponse> movieResponseList=MovieConverters.convertEntityToResponse(movieEntityList);
        return movieResponseList;
    }
    public List<MovieResponse> getByGenre(String genre){
        List<MovieEntity> movieEntityList=movieRepository.findBygenre(genre);
        List<MovieResponse> movieResponseList=MovieConverters.convertEntityToResponse(movieEntityList);
        return movieResponseList;
    }
    public MovieResponse getByName(String movie_name) throws Exception{
        MovieEntity movieEntity=movieRepository.findByName(movie_name);
        if (movieEntity.equals(null)){
            throw new Exception("Movie Not Found");
        }
        MovieResponse movieResponse=MovieResponse.builder()
                .id(movieEntity.getId())
                .movieName(movieEntity.getMovieName())
                .rating(movieEntity.getRating())
                .duration(movieEntity.getDuration())
                .language(movieEntity.getLanguage())
                .genre(movieEntity.getGenre())
                .build();
        return movieResponse;
    }
}
