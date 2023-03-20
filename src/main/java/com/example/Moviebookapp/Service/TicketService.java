package com.example.Moviebookapp.Service;

import com.example.Moviebookapp.Converters.TicketConverters;
import com.example.Moviebookapp.EntryDTOs.TicketEntryDto;
import com.example.Moviebookapp.Models.ShowEntity;
import com.example.Moviebookapp.Models.ShowSeatsEntity;
import com.example.Moviebookapp.Models.TicketEntity;
import com.example.Moviebookapp.Models.UserEntity;
import com.example.Moviebookapp.Repository.ShowRepository;
import com.example.Moviebookapp.Repository.ShowSeatsRepository;
import com.example.Moviebookapp.Repository.TicketRepository;
import com.example.Moviebookapp.Repository.UserRepository;
import com.example.Moviebookapp.ResponseDTOs.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowSeatsRepository showSeatsRepository;
    @Autowired
    JavaMailSender javaMailSender;

    public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception{
        //create ticket from EntryDto
        TicketEntity ticket= TicketConverters.convertDtoToEntity(ticketEntryDto);

        //Check the requested seats Availability
        boolean isValidRequest=checkValidityOfTickets(ticketEntryDto);

        if (isValidRequest==false){
            throw new Exception("Requested Seats are not available");
        }

        ShowEntity show=showRepository.findById(ticketEntryDto.getShowId()).get();

        List<ShowSeatsEntity>showSeatsEntityList=show.getListOfShowSeats();
        List<String>requestedSeats=ticketEntryDto.getRequestedSeats();

        //calculate amount
        int totalAmount=0;
        for (ShowSeatsEntity showSeatsEntity : showSeatsEntityList){
            String seatNo=showSeatsEntity.getSeatNo();
            if (requestedSeats.contains(seatNo)){
                totalAmount=totalAmount+showSeatsEntity.getPrice();
                showSeatsEntity.setIsbooked(true);
                showSeatsEntity.setBookedAt(new Date());
            }
        }
        ticket.setTotalAmount(totalAmount);//set the payble amount

        //other attributes
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getName());

        //setting the bookedTickets
        String allocatedSeats=getAllAiiocatedSeatsFromShowseats(requestedSeats);
        ticket.setBookedSeats(allocatedSeats);

        //Set all foreign attributes
        UserEntity user=userRepository.findById(ticketEntryDto.getUserId()).get();
        ticket.setShow(show);
        ticket.setUser(user);

        ticket=ticketRepository.save(ticket);

        //Now update TicketList
        List<TicketEntity>showTicketList=show.getListOfShowTickets();
        showTicketList.add(ticket);
        show.setListOfShowTickets(showTicketList);
        showRepository.save(show);

        List<TicketEntity>bookedTicketlist=user.getListOfTickets();
        bookedTicketlist.add(ticket);
        user.setListOfTickets(bookedTicketlist);
        userRepository.save(user);

        //Mail part need to
        String body = "Hi this is to confirm your booking for seat No "+allocatedSeats +"for the movie : " + ticket.getMovieName();


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("backeendacciojob@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);

        return "Ticket has sucessfully been added";
    }

    public String getAllAiiocatedSeatsFromShowseats(List<String>requestedSeats){
        String result="";
        for(String seat : requestedSeats){
            result =result+seat+", ";
        }
        return result;
    }
    public boolean checkValidityOfTickets(TicketEntryDto ticketEntryDto){
        ShowEntity show=showRepository.findById(ticketEntryDto.getShowId()).get();
        //requested seats
        List<String>requestedTickets=ticketEntryDto.getRequestedSeats();

        //show seats
        List<ShowSeatsEntity>allSeats=show.getListOfShowSeats();

        for (ShowSeatsEntity showSeatsEntity : allSeats){
            String seatNo=showSeatsEntity.getSeatNo();
            if (requestedTickets.contains(seatNo)){
                if (showSeatsEntity.isIsbooked()==true){
                    return false;//seat is already booked;
                }
            }
        }
        return true;
    }
    public TicketResponse getTicket(int id) throws Exception{
        TicketEntity ticketEntity=ticketRepository.findById(id).get();
        if (ticketEntity==null){
            throw new Exception("Invalid Ticket");
        }
        TicketResponse ticketResponse=TicketResponse.builder()
                .movieName(ticketEntity.getMovieName())
                .showTime(ticketEntity.getShowTime())
                .showDate(ticketEntity.getShowDate())
                .ticketId(ticketEntity.getTicketId())
                .theaterName(ticketEntity.getTheaterName())
                .bookedSeats(ticketEntity.getBookedSeats())
                .totalAmount(ticketEntity.getTotalAmount())
                .build();
        return ticketResponse;
    }
    public String cancelTicket(int id) throws Exception{
        TicketEntity ticketEntity=ticketRepository.findById(id).get();
        if (ticketEntity==null){
            throw new Exception("Invalid Ticket");
        }
        if (ticketEntity.getBookedSeats().equals("Booking Cancelled")){
            throw new Exception("Ticket already cancelled");
        }
        ShowEntity showEntity=ticketEntity.getShow();
        List<ShowSeatsEntity>showSeatsEntityList=showEntity.getListOfShowSeats();
        //finding all bookedTicket
        String str=ticketEntity.getBookedSeats();
        str = str.replaceAll(", ", " ");

        List<String>arr=new ArrayList<>();
        String x="";
        for(int i=0;i<str.length();i++){
            if (str.charAt(i)==' ' && x.length()>=2){
                arr.add(x);
                x="";
            } else{
                x =x+str.charAt(i);
            }
        }
        for (ShowSeatsEntity showSeatsEntity : showSeatsEntityList){
            String seatNo=showSeatsEntity.getSeatNo();
            if (arr.contains(seatNo)){
                showSeatsEntity.setBookedAt(null);
                showSeatsEntity.setIsbooked(false);
                showSeatsRepository.save(showSeatsEntity);
            }
        }
        ticketEntity.setBookedSeats("Booking Cancelled");
        ticketRepository.save(ticketEntity);
        return "You sucessfully cancelled your booking";
    }
}
