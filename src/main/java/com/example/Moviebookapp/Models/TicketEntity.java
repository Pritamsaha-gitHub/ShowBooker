package com.example.Moviebookapp.Models;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalAmount;
    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;
    private String ticketId= UUID.randomUUID().toString();
    private  String theaterName;
    private String bookedSeats;

    //user and ticket
    @ManyToOne
    @JoinColumn
    private UserEntity user;

    //ticket and show
    @ManyToOne
    @JoinColumn
    private ShowEntity show;

}
