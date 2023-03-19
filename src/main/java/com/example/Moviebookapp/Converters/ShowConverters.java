package com.example.Moviebookapp.Converters;

import com.example.Moviebookapp.EntryDTOs.ShowEntryDto;
import com.example.Moviebookapp.Models.ShowEntity;

public class ShowConverters {

    public static ShowEntity convertDtoToEntity (ShowEntryDto showEntryDto){
        ShowEntity showEntity=ShowEntity.builder()
                .showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType())
                .build();
        return showEntity;
    }
}
