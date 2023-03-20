package com.example.Moviebookapp.ResponseDTOs;

import com.example.Moviebookapp.Enums.Genre;
import com.example.Moviebookapp.Enums.Language;
//import jakarta.persistence.Column;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
import javax.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponse {
    private int id;
    private String movieName;
    private double rating;
    private int duration;
    private Language language;
    private Genre genre;
}
