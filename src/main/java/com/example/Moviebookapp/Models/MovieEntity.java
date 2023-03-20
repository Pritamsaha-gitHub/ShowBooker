package com.example.Moviebookapp.Models;

import com.example.Moviebookapp.Enums.Genre;
import com.example.Moviebookapp.Enums.Language;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String movieName;
    private double rating;
    private int duration;
    @Enumerated(value = EnumType.STRING)
    private Language language;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //show and movie
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<ShowEntity>listOfShows=new ArrayList<>();


}
