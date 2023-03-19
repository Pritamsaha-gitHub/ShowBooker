package com.example.Moviebookapp.Controller;

import com.example.Moviebookapp.EntryDTOs.TicketEntryDto;
import com.example.Moviebookapp.ResponseDTOs.TicketResponse;
import com.example.Moviebookapp.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody()TicketEntryDto ticketEntryDto){
        try {
            String result=ticketService.bookTicket(ticketEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result="Not able to book Ticket";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<TicketResponse> getTicket(@RequestParam("id") int id){
        try {
            TicketResponse ticketResponse=ticketService.getTicket(id);
            return new ResponseEntity<>(ticketResponse,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/cancel")
    public ResponseEntity<String> cancelTicket(@RequestParam("id") int id){
        try{
            String result=ticketService.cancelTicket(id);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
