package com.example.Moviebookapp.EntryDTOs;

import lombok.Data;

@Data
public class TheaterEntryDTO {
    private String name;
    private String location;

    private int goldSeatCount;

    private int silverSeatCount;
    private int platinumSeatCount;
}
