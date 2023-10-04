package com.example.controller;

import com.example.repository.request.HotelRequest;
import com.example.repository.response.BaseResponse;
import com.example.repository.response.HotelBookingResponse;
import com.example.repository.response.HotelResponse;
import com.example.repository.service.QuoteService;
import com.example.validation.QuoteValidator;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ExecuteOn(TaskExecutors.IO)
@Controller
public class QuoteController {

    @Inject
    private final QuoteValidator quoteValidator;

    @Inject
    private final QuoteService quoteService;

    public QuoteController(QuoteValidator quoteValidator, QuoteService quoteService) {
        this.quoteValidator = quoteValidator;
        this.quoteService = quoteService;
    }

    @Post(value = "/quotation", processes = MediaType.APPLICATION_JSON)
    public HttpResponse<BaseResponse> create(@Valid @Body HotelRequest hotelRequest) {
        HotelResponse hotelResponse = null;
        try {
            quoteValidator.validateHotelRequest(hotelRequest);
             hotelResponse = quoteService.createQuote(hotelRequest);
        }catch (ValidationException e) {
            String errorMessage = e.getMessage();
        }
        return HttpResponse.ok(new BaseResponse(hotelResponse));
    }

    @Get("/quotations")
    public HttpResponse<BaseResponse> getQuotations(){
        HotelBookingResponse hotelBookingResponse= quoteService.getQuotations();
        return HttpResponse.ok(new BaseResponse(hotelBookingResponse));
    }

    @Get("/quotation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<BaseResponse> getQuotationById(@PathVariable long id) {
        return quoteService.getQuatationById(id);
    }

    @Put("/quotation/{id}")
    public HttpResponse<BaseResponse> updateQuotation(@PathVariable Long id, @Body @Valid HotelRequest hotelRequest){
        return quoteService.updateQuote(id,hotelRequest);
    }

}
