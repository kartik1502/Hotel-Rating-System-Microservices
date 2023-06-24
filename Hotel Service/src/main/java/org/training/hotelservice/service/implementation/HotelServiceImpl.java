package org.training.hotelservice.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.training.hotelservice.dto.HotelDto;
import org.training.hotelservice.dto.Response;
import org.training.hotelservice.entity.Hotel;
import org.training.hotelservice.exception.ResourceConflict;
import org.training.hotelservice.exception.ResourceNotFound;
import org.training.hotelservice.repository.HotelRepository;
import org.training.hotelservice.service.HotelService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Value("${spring.application.responseCode}")
    private String responseCode;

    @Override
    public Response addHotel(HotelDto hotelDto) {

        Optional<Hotel> presentHotel = hotelRepository.findHotelByNameAndLocation(hotelDto.getName(), hotelDto.getLocation());
        if(presentHotel.isPresent()){
            throw new ResourceConflict("Hotel with same name and location already present");
        }
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelDto, hotel);
        hotel.setHotelId(UUID.randomUUID().toString());
        hotelRepository.save(hotel);
        return new Response(responseCode, "Hotel Added Successfully");
    }

    @Override
    public List<HotelDto> getAllHotels() {

        return hotelRepository.findAll().stream().map(hotel -> {
            HotelDto hotelDto = new HotelDto();
            BeanUtils.copyProperties(hotel, hotelDto, "hotelId");
            return hotelDto;
        }).collect(Collectors.toList());
    }

    @Override
    public HotelDto getHotelById(String hotelId) {

        return hotelRepository.findById(hotelId).map(hotel -> {
            HotelDto hotelDto = new HotelDto();
            BeanUtils.copyProperties(hotel, hotelDto, "hotelId");
            return hotelDto;
        }).orElseThrow(() -> new ResourceNotFound("Hotel with hotel Id: "+hotelId+" not found"));
    }
}
