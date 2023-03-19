package com.example.Moviebookapp.EntryDTOs;

import com.example.Moviebookapp.Enums.ShowType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {
    private LocalDate localDate;
    private LocalTime localTime;

    private ShowType showType;
    private int movieId;
    private int theaterId;
    private int goldSeatPrice;
    private int silverSeatPrice;
    private int platinumSeatPrice;
}
