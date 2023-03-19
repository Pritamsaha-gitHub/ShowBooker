package com.example.Moviebookapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;

    //theater and theaterseat
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeatsEntity>listOfTheaterseats=new ArrayList<>();

    //theater and show
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<ShowEntity>listOfShows=new ArrayList<>();
}
