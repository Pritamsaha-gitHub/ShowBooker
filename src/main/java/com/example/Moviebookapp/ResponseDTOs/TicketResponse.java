package com.example.Moviebookapp.ResponseDTOs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class TicketResponse {
    private int totalAmount;
    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;
    private String ticketId;
    private  String theaterName;
    private String bookedSeats;
}
