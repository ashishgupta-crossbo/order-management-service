package com.example.repository.service;

import com.example.repository.request.HotelRequest;
import com.example.repository.response.BaseResponse;
import com.example.repository.response.HotelBookingResponse;
import com.example.repository.response.HotelResponse;
import io.micronaut.http.HttpResponse;

import java.util.List;

public interface QuoteService {
    HotelResponse createQuote(HotelRequest hotelRequest);

    HotelBookingResponse  getQuotations();

    HttpResponse<BaseResponse> getQuatationById(long id);

    HttpResponse<BaseResponse> updateQuote(long id , HotelRequest hotelRequest);
}
