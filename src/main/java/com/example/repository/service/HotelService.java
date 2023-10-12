package com.example.repository.service;

import com.example.dto.response.hotel.AvailabilityResponseDto;
import com.example.dto.response.hotel.HotelResponseDto;
import com.example.dto.response.hotel.RoomResponseDto;

import java.util.List;

public interface HotelService {

    HotelResponseDto getHotelInfo(String hotelCode);

    HotelResponseDto getHotelsInfo(String hotelCode);

    RoomResponseDto getHotelInfoWithRooms(String hotelCode);

    AvailabilityResponseDto getHotelWithMinimumPriceRange(String hotelCode);

    void checkRoomAvailability(List<String> hotelCodes, int adultsCount, String dateCheckIn, String dateCheckOut, int childrenCount, List<String> promoCodes);

}
