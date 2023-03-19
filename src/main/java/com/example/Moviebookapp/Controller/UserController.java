package com.example.Moviebookapp.Controller;

import com.example.Moviebookapp.EntryDTOs.UserEntryDTO;
import com.example.Moviebookapp.ResponseDTOs.UserResponse;
import com.example.Moviebookapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody()UserEntryDTO userEntryDTO){
        try {
            String result= userService.addUser(userEntryDTO);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result="User could not be added";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/change_email")
    public ResponseEntity<String> changeEmail(@RequestParam("id") int id ,String email){
        try{
            String result=userService.changeEmail(id,email);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(Exception e){
            String result="Invalid user";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/change_mobileNo")
    public ResponseEntity<String> changeMobileNo(@RequestParam("id") int id,String mobileNo){
        try{
            String result=userService.changeMobileNo(id,mobileNo);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result="Invalid user";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/change_address")
    public ResponseEntity<String> changeAddress(@RequestParam("id") int id,String address){
        try{
            String result=userService.changeAddress(id,address);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result="Invalid user";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_user")
    public ResponseEntity<UserResponse> getUser(@RequestParam("id") int id){
        try {
            UserResponse userResponse=userService.getUser(id);
            return new ResponseEntity<>(userResponse,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
