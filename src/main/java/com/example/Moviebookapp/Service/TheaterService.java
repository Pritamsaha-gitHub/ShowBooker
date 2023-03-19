package com.example.Moviebookapp.Service;

import com.example.Moviebookapp.Converters.TheaterConverters;
import com.example.Moviebookapp.EntryDTOs.TheaterEntryDTO;
import com.example.Moviebookapp.Enums.SeatType;
import com.example.Moviebookapp.Models.TheaterEntity;
import com.example.Moviebookapp.Models.TheaterSeatsEntity;
import com.example.Moviebookapp.Repository.TheaterRepository;
import com.example.Moviebookapp.Repository.TheaterSeatsRepository;
import com.example.Moviebookapp.ResponseDTOs.TheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterSeatsRepository theaterSeatsRepository;

    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(TheaterEntryDTO theaterEntryDTO) throws Exception{

        if (theaterEntryDTO.getName()==null || theaterEntryDTO.getLocation()==null){
            throw new Exception("Name and location should valid");
        }
        TheaterEntity theaterEntity= TheaterConverters.convertDtoToEntity(theaterEntryDTO);
        List<TheaterSeatsEntity>theaterSeatsEntityList=createTheaterSeats(theaterEntryDTO,theaterEntity);
        theaterEntity.setListOfTheaterseats(theaterSeatsEntityList);
        theaterRepository.save(theaterEntity);
        return "Theater Sucessfully Added";
    }
    private List<TheaterSeatsEntity> createTheaterSeats(TheaterEntryDTO theaterEntryDto,TheaterEntity theaterEntity){

        int noOfGoldSeat=theaterEntryDto.getGoldSeatCount();
        int noOfSilverSeat=theaterEntryDto.getSilverSeatCount();
        int noOfPlatinumSeat=theaterEntryDto.getPlatinumSeatCount();

       List<TheaterSeatsEntity> theaterSeatEntityList = new ArrayList<>();

        //Created the Gold Seats
        for(int count = 1;count<=noOfGoldSeat;count++){

            //We need to make a new TheaterSeatEntity
            TheaterSeatsEntity theaterSeatEntity = TheaterSeatsEntity.builder()
                    .seatType(SeatType.GOLD)
                    .seatNo(count+"G")
                    .theater(theaterEntity)
                            .build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }


       //Create the silver Seats
        for(int count=1;count<=noOfSilverSeat;count++){

            TheaterSeatsEntity theaterSeatEntity = TheaterSeatsEntity.builder().
                    seatType(SeatType.SILVER)
                    .seatNo(count+"S")
                    .theater(theaterEntity)
                    .build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        //Created the plaatinum Seats
        for(int count = 1;count<=noOfPlatinumSeat;count++){

            //We need to make a new TheaterSeatEntity
            TheaterSeatsEntity theaterSeatEntity = TheaterSeatsEntity.builder()
                    .seatType(SeatType.PLATINUM)
                    .seatNo(count+"P")
                    .theater(theaterEntity)
                    .build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        return theaterSeatEntityList;
    }
    public List<TheaterResponse> getByLocation(String location){
        List<TheaterEntity>theaterEntityList=theaterRepository.findBylocation(location);
        List<TheaterResponse>ansList=TheaterConverters.convertEntityToResponse(theaterEntityList);
        return ansList;
    }
}
