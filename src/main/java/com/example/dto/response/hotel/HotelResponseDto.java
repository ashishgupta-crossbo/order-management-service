package com.example.dto.response.hotel;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class HotelResponseDto {
    private String name;
    private String title;
    private String defaultCurrency;
    private String latitude;
    private String longitude;
    private List<ImageDto> images;
    private List<VideoDto> videos;
    private String address;
    private String email;
    private String phone;
    private String countryName;
    private String countryIsoCode;
    private String city;
    private boolean openForBooking;
    private String hotelCode;
    private String hotelId;
    private int starRating;
    private String ratingDescription;
}
