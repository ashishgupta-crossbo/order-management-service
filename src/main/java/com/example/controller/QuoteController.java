package com.example.controller;

import com.example.repository.request.CreateQuoteRequest;
import com.example.repository.response.BaseResponse;
import com.example.repository.response.getQuotationReqestList;
import com.example.repository.response.CreateQuoteResponseDto;
import com.example.repository.response.QuotationResponse;
import com.example.repository.service.QuoteService;
import com.example.validation.QuoteValidator;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

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
    public HttpResponse<BaseResponse> create(@Valid @Body CreateQuoteRequest hotelRequest) {
            quoteValidator.validateHotelRequest(hotelRequest);
         CreateQuoteResponseDto createQuoteResponseDto = quoteService.createQuote(hotelRequest);
        return HttpResponse.ok(new BaseResponse(createQuoteResponseDto));
    }

    @Get("/quotations")
    public HttpResponse<BaseResponse> getQuotations(){
        getQuotationReqestList hotelBookingResponse= quoteService.getQuotations();
        BaseResponse<getQuotationReqestList> response = new BaseResponse<>(true, hotelBookingResponse, null);
        return HttpResponse.ok((response));
    }

    @Get("/quotation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<BaseResponse> getQuotationById(@PathVariable long id) {
        QuotationResponse quotationResponse= quoteService.getQuatationById(id);
        return HttpResponse.ok(new BaseResponse<>(quotationResponse));
    }

    @Put("/quotation/{id}")
    public HttpResponse<BaseResponse> updateQuotation(@PathVariable Long id, @Body @Valid CreateQuoteRequest hotelRequest){
        quoteValidator.validateHotelRequest(hotelRequest);
        quoteService.updateQuote(id,hotelRequest);
         return HttpResponse.ok(new BaseResponse(true,null,null));
    }

}
