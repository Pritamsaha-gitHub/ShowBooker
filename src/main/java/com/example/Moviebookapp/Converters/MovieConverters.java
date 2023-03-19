package com.example.Moviebookapp.Converters;

import com.example.Moviebookapp.EntryDTOs.MovieEntryDTO;
import com.example.Moviebookapp.Models.MovieEntity;
import com.example.Moviebookapp.ResponseDTOs.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieConverters {

    public static MovieEntity convertDtoToEntity(MovieEntryDTO movieEntryDTO){
        MovieEntity movieEntity=MovieEntity.builder()
                .movieName(movieEntryDTO.getMovieName())
                .rating(movieEntryDTO.getRatings())
                .duration(movieEntryDTO.getDuration())
                .language(movieEntryDTO.getLanguage())
                .genre(movieEntryDTO.getGenre())
                .build();
        return movieEntity;
    }
    public static List<MovieResponse> convertEntityToResponse(List<MovieEntity> movieEntityList){
        List<MovieResponse> movieResponseList=new ArrayList<>();
        for (MovieEntity movieEntity : movieEntityList){
            MovieResponse movieResponse=MovieResponse.builder()
                    .id(movieEntity.getId())
                    .movieName(movieEntity.getMovieName())
                    .rating(movieEntity.getRating())
                    .duration(movieEntity.getDuration())
                    .language(movieEntity.getLanguage())
                    .genre(movieEntity.getGenre())
                    .build();
            movieResponseList.add(movieResponse);
        }

        return movieResponseList;
    }
}
