package com.example.controller;
import com.example.dto.request.QuotationRequest;
import com.example.dto.response.BaseResponse;
import com.example.dto.response.quote.QuotationResponseList;
import com.example.dto.response.quote.QuotationResponseDto;
import com.example.dto.response.quote.QuotationResponse;
import com.example.repository.service.QuoteService;
import com.example.validation.QuotationValidator;
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
    private final QuotationValidator quoteValidator;

    @Inject
    private final QuoteService quoteService;

    public QuoteController(QuotationValidator quoteValidator, QuoteService quoteService) {
        this.quoteValidator = quoteValidator;
        this.quoteService = quoteService;
    }

    @Post(value = "/quotation", processes = MediaType.APPLICATION_JSON)
    public HttpResponse<BaseResponse<QuotationResponseDto>> create(@Valid @Body QuotationRequest quotationRequest) {
            quoteValidator.validateQuotationRequest(quotationRequest);
         QuotationResponseDto quotationResponseDto = quoteService.createQuote(quotationRequest);
        return HttpResponse.ok(new BaseResponse<>(quotationResponseDto));
    }

    @Get("/quotations")
    public HttpResponse<BaseResponse<QuotationResponseList>> getQuotations(){
        QuotationResponseList quotationResponseList= quoteService.getQuotations();
        return HttpResponse.ok((new BaseResponse<>(quotationResponseList)));
    }

    @Get("/quotation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<BaseResponse<QuotationResponse>> getQuotationById(@PathVariable long id) {
        QuotationResponse quotationResponse= quoteService.getQuatationById(id);
        return HttpResponse.ok(new BaseResponse<>(quotationResponse));
    }

    @Put("/quotation/{id}")
    public HttpResponse<BaseResponse<String>> updateQuotation(@PathVariable Long id, @Body @Valid QuotationRequest hotelRequest){
        quoteValidator.validateQuotationRequest(hotelRequest);
        quoteService.updateQuote(id,hotelRequest);
         return HttpResponse.ok(new BaseResponse<>());
    }

    @Delete("/quotation")
    public HttpResponse<BaseResponse<String>>deleteQuote(@QueryValue long id){
        quoteService.deleteQuote(id);
        return HttpResponse.ok(new BaseResponse<>());
    }
}
