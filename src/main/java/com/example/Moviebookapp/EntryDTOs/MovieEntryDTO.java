package com.example.Moviebookapp.EntryDTOs;

import com.example.Moviebookapp.Enums.Genre;
import com.example.Moviebookapp.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDTO {
    private String movieName;

    private double ratings;

    private int duration;

    private Language language;

    private Genre genre;
}
