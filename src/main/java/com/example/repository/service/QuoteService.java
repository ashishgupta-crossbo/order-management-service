package com.example.repository.service;

import com.example.dto.request.QuotationRequest;
import com.example.dto.response.quote.QuotationResponseList;
import com.example.dto.response.quote.QuotationResponseDto;
import com.example.dto.response.quote.QuotationResponse;

public interface QuoteService {
    QuotationResponseDto createQuote(QuotationRequest hotelRequest);

   QuotationResponseList getQuotations();

    QuotationResponse getQuatationById(long id);

  void updateQuote(long id , QuotationRequest hotelRequest);

    void deleteQuote(long quoteId);
}
