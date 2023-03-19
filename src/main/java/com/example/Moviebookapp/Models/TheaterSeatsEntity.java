package com.example.Moviebookapp.Models;

import com.example.Moviebookapp.Enums.SeatType;
import com.example.Moviebookapp.Enums.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seat")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterSeatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private String seatNo;

    //theater and theaterseat
    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;
}
