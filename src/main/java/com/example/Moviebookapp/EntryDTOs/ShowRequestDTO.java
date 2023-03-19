package com.example.Moviebookapp.EntryDTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShowRequestDTO {
    private String theaterName;
    private LocalDate showDate;
}
