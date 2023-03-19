package com.example.Moviebookapp.Converters;

import com.example.Moviebookapp.EntryDTOs.TicketEntryDto;
import com.example.Moviebookapp.Models.TicketEntity;

public class TicketConverters {

    public static TicketEntity convertDtoToEntity(TicketEntryDto ticketEntryDto){
        TicketEntity ticketEntity=new TicketEntity();
        return ticketEntity;
    }
}
