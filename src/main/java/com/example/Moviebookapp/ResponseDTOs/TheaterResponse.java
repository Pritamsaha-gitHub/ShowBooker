package com.example.Moviebookapp.ResponseDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheaterResponse {
    private int id;
    private String name;
    private String location;
}
