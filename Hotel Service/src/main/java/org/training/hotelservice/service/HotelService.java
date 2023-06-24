package org.training.hotelservice.service;

import org.training.hotelservice.dto.HotelDto;
import org.training.hotelservice.dto.Response;

import java.util.List;

public interface HotelService {
    Response addHotel(HotelDto hotelDto);

    List<HotelDto> getAllHotels();

    HotelDto getHotelById(String hotelId);
}
