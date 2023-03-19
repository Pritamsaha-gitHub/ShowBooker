package com.example.Moviebookapp.Models;

import com.example.Moviebookapp.Enums.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    private LocalDate showDate;
    private LocalTime showTime;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    //show and showseat
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeatsEntity>listOfShowSeats=new ArrayList<>();

    //show and movie
    @ManyToOne
    @JoinColumn
    private MovieEntity movie;

    //theater and show
    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;

    //ticket and show
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<TicketEntity>listOfShowTickets=new ArrayList<>();


}
