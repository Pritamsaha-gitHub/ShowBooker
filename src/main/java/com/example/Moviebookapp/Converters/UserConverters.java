package com.example.Moviebookapp.Converters;

import com.example.Moviebookapp.EntryDTOs.UserEntryDTO;
import com.example.Moviebookapp.Models.UserEntity;

public class UserConverters {
    public static UserEntity convertDtoToEntity(UserEntryDTO userEntryDTO){
        UserEntity userEntity=UserEntity.builder()
                .name(userEntryDTO.getName())
                .age(userEntryDTO.getAge())
                .address(userEntryDTO.getAddress())
                .email(userEntryDTO.getEmail())
                .mobileNo(userEntryDTO.getMobileNo())
                .build();
        return userEntity;
    }
}
