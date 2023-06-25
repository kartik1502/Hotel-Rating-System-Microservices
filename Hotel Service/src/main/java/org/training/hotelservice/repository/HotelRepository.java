package org.training.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.hotelservice.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    Optional<Hotel> findHotelByNameAndLocation(String name, String location);

    List<Hotel> findAllByHotelIdIn(List<String> hotelIds);
}
