package com.example.validation;

import com.example.repository.common.ErrorCode;
import com.example.exceptions.RoomNotFoundException;
import com.example.repository.request.HotelRequest;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;

@Singleton
public class QuoteValidator {

    public static boolean checkRequest(HotelRequest hotelRequest){

        return  (!(StringUtils.isEmpty(hotelRequest.getHotelCode())) || StringUtils.isEmpty(hotelRequest.getDateCheckIn()) || StringUtils.isEmpty(hotelRequest.getDateCheckOut()) ||
                StringUtils.isEmpty(hotelRequest.getRatePlanCode()) || StringUtils.isEmpty(hotelRequest.getTotalTaxAmount()) ||
                StringUtils.isEmpty(hotelRequest.getTotalAmountAfterTax()) || StringUtils.isEmpty(hotelRequest.getTotalAmountBeforeTax())
                || StringUtils.isEmpty(hotelRequest.getRequestRooms().get(0).getRatePlanCode()) || StringUtils.isEmpty(hotelRequest.getRequestRooms().get(0).getRoomTypeCode())
        || StringUtils.isEmpty(hotelRequest.getRequestRooms().get(0).getPromoCode()));
    }

    public void validateHotelRequest(HotelRequest hotelRequest) {
        if (hotelRequest.getRequestRooms()==null){
            throw new RoomNotFoundException(ErrorCode.ROOM_NOT_FOUND_EXCEPTION);
        }
    }
}
