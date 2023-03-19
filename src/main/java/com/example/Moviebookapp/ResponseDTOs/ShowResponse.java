package com.example.Moviebookapp.ResponseDTOs;

import com.example.Moviebookapp.Enums.Language;
import com.example.Moviebookapp.Enums.ShowType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Builder
public class ShowResponse {
    private String movieName;
    private Language language;
    private ShowType showType;
    private LocalTime showTime;
    private LocalDate showDate;
}
