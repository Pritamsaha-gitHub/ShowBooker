package com.example.Moviebookapp.ResponseDTOs;

import javax.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private int id;

    private String name;
    private String email;
    private  int age;
    private String mobileNo;
    private String address;
}
