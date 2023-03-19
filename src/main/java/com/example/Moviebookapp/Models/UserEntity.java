package com.example.Moviebookapp.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private  int age;
    @Column(unique = true)
    private String mobileNo;
    private String address;

    //user and ticket
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<TicketEntity>listOfTickets=new ArrayList<>();
}
