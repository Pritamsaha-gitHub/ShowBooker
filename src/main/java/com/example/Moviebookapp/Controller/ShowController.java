package com.example.Moviebookapp.Controller;

import com.example.Moviebookapp.EntryDTOs.ShowEntryDto;
import com.example.Moviebookapp.EntryDTOs.ShowRequestDTO;
import com.example.Moviebookapp.ResponseDTOs.ShowResponse;
import com.example.Moviebookapp.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody()ShowEntryDto showEntryDto){
        try {
            String result=showService.addShow(showEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result="Show could not added";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_upcoming_show")
    public ResponseEntity<List<ShowResponse>> upcomingShow(@RequestBody()ShowRequestDTO showRequestDTO){
        List<ShowResponse> showResponseList=showService.upcomingShow(showRequestDTO);
        return new ResponseEntity<>(showResponseList,HttpStatus.ACCEPTED);
    }
}
