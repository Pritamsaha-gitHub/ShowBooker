package com.example.Moviebookapp.Models;

import com.example.Moviebookapp.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seat")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowSeatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isbooked;
    private int price;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Date bookedAt;



    @ManyToOne
    @JoinColumn
    private ShowEntity show;
}
