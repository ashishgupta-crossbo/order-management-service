package com.example.repository.service.impl;

import com.example.exceptions.CustomException;
import com.example.common.ErrorCode;
import com.example.dto.response.hotel.AvailabilityResponseDto;
import com.example.dto.response.hotel.HotelResponseDto;
import com.example.dto.response.hotel.RoomResponseDto;
import com.example.repository.service.HotelService;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class HotelServiceImpl implements HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class.getName());

    @Override
    public HotelResponseDto getHotelInfo(String hotelCode) {
        try {
            // hit third party api to get the data
        }catch (Exception e){
            logger.info("Getting error in getHotelInfo function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
        return null;
    }

    @Override
    public HotelResponseDto getHotelsInfo(String hotelCode) {
        try {
            // hit third party api to get the data
        }catch (Exception e){
            logger.info("Getting error in getHotelInfo function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
        return null;
    }

    @Override
    public RoomResponseDto getHotelInfoWithRooms(String hotelCode) {
        try {
            // hit third party api to get the data
        }catch (Exception e){
            logger.info("Getting error in getHotelInfoWithRooms function {}" ,e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);

        }
        return null;
    }

    @Override
    public AvailabilityResponseDto getHotelWithMinimumPriceRange(String hotelCode) {
        try {
            // hit third party api to get the data
        }catch (Exception e){
            logger.info("Getting error in getHotelWithMinimumPriceRange function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);

        }
        return null;
    }

    @Override
    public void checkRoomAvailability(List<String> hotelCodes, int adultsCount, String dateCheckIn, String dateCheckOut, int childrenCount, List<String> promoCodes) {
        try {
            // hit third party api to get the data
        }catch (Exception e){
            logger.info("Getting error in checkRoomAvailability function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);

        }
    }
}
