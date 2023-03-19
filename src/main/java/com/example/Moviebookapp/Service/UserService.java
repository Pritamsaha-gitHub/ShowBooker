package com.example.Moviebookapp.Service;

import com.example.Moviebookapp.Converters.UserConverters;
import com.example.Moviebookapp.EntryDTOs.UserEntryDTO;
import com.example.Moviebookapp.Models.UserEntity;
import com.example.Moviebookapp.Repository.UserRepository;
import com.example.Moviebookapp.ResponseDTOs.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(UserEntryDTO userEntryDTO) throws Exception{
        UserEntity userEntity= UserConverters.convertDtoToEntity(userEntryDTO);
        userRepository.save(userEntity);
        return "User Sucessfully added";
    }
    public String changeEmail(int id,String email) throws Exception{
        UserEntity userEntity=userRepository.findById(id).get();
        if (userEntity==null){
            throw new Exception("Invalid User");
        }
        userEntity.setEmail(email);
        userRepository.save(userEntity);
        return "Email Sucessfully Updated";
    }

    public String changeMobileNo(int id,String mobileNo) throws Exception{
        UserEntity userEntity=userRepository.findById(id).get();
        if (userEntity==null){
            throw new Exception("Invalid User");
        }
        userEntity.setMobileNo(mobileNo);
        userRepository.save(userEntity);
        return "Mobile-Number Sucessfully Updated";
    }
    public String changeAddress(int id,String address) throws Exception{
        UserEntity userEntity=userRepository.findById(id).get();
        if (userEntity==null){
            throw new Exception("Invalid User");
        }
        userEntity.setAddress(address);
        userRepository.save(userEntity);
        return "Address Sucessfully Updated";
    }
    public UserResponse getUser(int id)throws Exception{
        UserEntity userEntity=userRepository.findById(id).get();
        if (userEntity==null){
            throw new Exception("Invalid User");
        }
        UserResponse userResponse=UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .age(userEntity.getAge())
                .address(userEntity.getAddress())
                .email(userEntity.getEmail())
                .mobileNo(userEntity.getMobileNo())
                .build();
        return userResponse;
    }
}
