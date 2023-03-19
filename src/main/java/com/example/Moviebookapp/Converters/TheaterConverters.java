package com.example.Moviebookapp.Converters;

import com.example.Moviebookapp.EntryDTOs.TheaterEntryDTO;
import com.example.Moviebookapp.Models.TheaterEntity;
import com.example.Moviebookapp.ResponseDTOs.TheaterResponse;

import java.util.ArrayList;
import java.util.List;

public class TheaterConverters {

    public static TheaterEntity convertDtoToEntity(TheaterEntryDTO theaterEntryDTO){
        TheaterEntity theaterEntity=TheaterEntity.builder()
                .name(theaterEntryDTO.getName())
                .location(theaterEntryDTO.getLocation())
                .build();
        return theaterEntity;
    }
    public static List<TheaterResponse> convertEntityToResponse(List<TheaterEntity> theaterEntityList){
        List<TheaterResponse> ansList=new ArrayList<>();

        for (TheaterEntity theaterEntity : theaterEntityList){
            TheaterResponse theaterResponse=TheaterResponse.builder()
                    .id(theaterEntity.getId())
                    .name(theaterEntity.getName())
                    .location(theaterEntity.getLocation())
                    .build();
            ansList.add(theaterResponse);
        }
        return ansList;
    }
}
