package com.example.Moviebookapp.EntryDTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketEntryDto {
    private int showId;
    private List<String>requestedSeats=new ArrayList<>();
    private int userId;
}