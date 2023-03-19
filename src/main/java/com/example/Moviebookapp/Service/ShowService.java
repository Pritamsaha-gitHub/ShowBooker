package com.example.Moviebookapp.Service;

import com.example.Moviebookapp.Converters.ShowConverters;
import com.example.Moviebookapp.EntryDTOs.ShowEntryDto;
import com.example.Moviebookapp.EntryDTOs.ShowRequestDTO;
import com.example.Moviebookapp.Enums.SeatType;
import com.example.Moviebookapp.Models.*;
import com.example.Moviebookapp.Repository.MovieRepository;
import com.example.Moviebookapp.Repository.ShowRepository;
import com.example.Moviebookapp.Repository.TheaterRepository;
import com.example.Moviebookapp.ResponseDTOs.ShowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    MovieRepository movieRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception{
        ShowEntity showEntity= ShowConverters.convertDtoToEntity(showEntryDto);
        MovieEntity movie=movieRepository.findById(showEntryDto.getMovieId()).get();
        TheaterEntity theater=theaterRepository.findById(showEntryDto.getTheaterId()).get();

        if (theater==null){
            throw new Exception("Theater not found");
        }
        if (movie==null){
            throw new Exception("Movie is not found");
        }
        //set movie and theater in show
        showEntity.setTheater(theater);
        showEntity.setMovie(movie);

        List<ShowSeatsEntity>showSeatsList=createShowSeatEntiy(showEntryDto,showEntity);
        showEntity.setListOfShowSeats(showSeatsList);

        //Now time to save data
        showEntity=showRepository.save(showEntity);
        movie.getListOfShows().add(showEntity);
        theater.getListOfShows().add(showEntity);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        return "Show sucessfully added";
    }

    public List<ShowSeatsEntity> createShowSeatEntiy(ShowEntryDto showEntryDto,ShowEntity showEntity){
        TheaterEntity theaterEntity=showEntity.getTheater();
        List<TheaterSeatsEntity>theaterSeatsEntityList=theaterEntity.getListOfTheaterseats();

        List<ShowSeatsEntity>showSeatsList=new ArrayList<>();
        for (TheaterSeatsEntity theaterSeatsEntity : theaterSeatsEntityList){
            ShowSeatsEntity showSeatsEntity=new ShowSeatsEntity();
            showSeatsEntity=ShowSeatsEntity.builder()
                    .seatNo(theaterSeatsEntity.getSeatNo())
                    .seatType(theaterSeatsEntity.getSeatType())
                    .build();
            if ((theaterSeatsEntity.getSeatType().equals(SeatType.GOLD))){
                showSeatsEntity.setPrice(showEntryDto.getGoldSeatPrice());
            }else if (theaterSeatsEntity.getSeatType().equals(SeatType.SILVER)){
                showSeatsEntity.setPrice(showEntryDto.getSilverSeatPrice());
            }else {
                showSeatsEntity.setPrice(showEntryDto.getPlatinumSeatPrice());
            }
            showSeatsEntity.setIsbooked(false);
            showSeatsEntity.setShow(showEntity);
            showSeatsList.add(showSeatsEntity);
        }
        return showSeatsList;
    }
    public List<ShowResponse> upcomingShow(ShowRequestDTO showRequestDTO){
        TheaterEntity theaterEntity=theaterRepository.findByName(showRequestDTO.getTheaterName());
        List<ShowEntity> showEntityList=theaterEntity.getListOfShows();

        List<ShowResponse> showResponseList=new ArrayList<>();

        for (ShowEntity showEntity : showEntityList){
            String a=showEntity.getShowDate().toString();//originalDate
            String b=showRequestDTO.getShowDate().toString();//recentDate
            int minLength=Math.min(a.length(),b.length());
            int ans=0;

            for(int i=0;i<minLength;i++){
                int f=a.charAt(i);
                int s=b.charAt(i);
                ans=f-s;
                if(ans !=0){
                    break;
                }
            }
            if (ans>=0){
                MovieEntity movie=showEntity.getMovie();
                ShowResponse showResponse=ShowResponse.builder()
                        .movieName(movie.getMovieName())
                        .language(movie.getLanguage())
                        .showType(showEntity.getShowType())
                        .showTime(showEntity.getShowTime())
                        .showDate(showEntity.getShowDate())
                        .build();
                showResponseList.add(showResponse);
            }
        }
        return showResponseList;
    }
}
