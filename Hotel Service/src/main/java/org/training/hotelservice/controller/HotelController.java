package org.training.hotelservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.training.hotelservice.dto.HotelDto;
import org.training.hotelservice.dto.Response;
import org.training.hotelservice.service.HotelService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Response> addHotel(@RequestBody @Valid HotelDto hotelDto){
        return new ResponseEntity<>(hotelService.addHotel(hotelDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }


    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable String hotelId){
        return new ResponseEntity<>(hotelService.getHotelById(hotelId), HttpStatus.OK);
    }
}
