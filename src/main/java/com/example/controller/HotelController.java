package com.example.controller;
import com.example.dto.response.BaseResponse;
import com.example.dto.response.hotel.AvailabilityResponseDto;
import com.example.dto.response.hotel.HotelResponseDto;
import com.example.dto.response.hotel.RoomResponseDto;
import com.example.repository.service.HotelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import java.util.List;

@Controller
@ExecuteOn(TaskExecutors.IO)
public class HotelController {

    @Inject
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Get("/hotels/info/{hotelCode}")
    public HttpResponse<BaseResponse<HotelResponseDto>>getHotelInfo(@PathVariable String hotelCode){
       HotelResponseDto hotelDetailDto= hotelService.getHotelsInfo(hotelCode);
        return HttpResponse.ok(new BaseResponse<>(hotelDetailDto));
    }

    @Get("/hotel/info/{hotelCode}")
    public HttpResponse<BaseResponse<HotelResponseDto>>getHotelsInfo(@PathVariable String hotelCode){
        HotelResponseDto hotelDetailDto= hotelService.getHotelInfo(hotelCode);
        return HttpResponse.ok(new BaseResponse<>(hotelDetailDto));
    }

    @Get("/hotels/info/{hotelCode}/rooms")
    public HttpResponse<BaseResponse<RoomResponseDto>>getHotelInfoWithRoomInfo(@PathVariable String hotelCode){
       RoomResponseDto roomResponseDto= hotelService.getHotelInfoWithRooms(hotelCode);
        return HttpResponse.ok(new BaseResponse<>(roomResponseDto));
    }

    @Get("/hotels/book/{hotelCode}/min_price_calendar")
    public HttpResponse<BaseResponse<AvailabilityResponseDto>>getMinimumPrizeRangeHotel(@PathVariable String hotelCode){
        AvailabilityResponseDto availabilityResponseDto=hotelService.getHotelWithMinimumPriceRange(hotelCode);
        return HttpResponse.ok(new BaseResponse<>(availabilityResponseDto));
    }

    @Get("/hotels/book/v1/multi-availability")
    public HttpResponse<BaseResponse<String>>checkRoomAvailability(  @QueryValue List<String> hotelCodes, @QueryValue int adultsCount, @QueryValue String dateCheckIn,
                                                                     @QueryValue String dateCheckOut, @QueryValue int childrenCount, @QueryValue List<String> promoCodes){

      hotelService.checkRoomAvailability(hotelCodes,adultsCount,dateCheckIn,dateCheckOut,childrenCount,promoCodes);
        return HttpResponse.ok(new BaseResponse<>());
    }
}
