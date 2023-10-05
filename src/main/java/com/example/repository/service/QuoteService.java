package com.example.repository.service;

import com.example.repository.request.CreateQuoteRequest;
import com.example.repository.response.getQuotationReqestList;
import com.example.repository.response.CreateQuoteResponseDto;
import com.example.repository.response.QuotationResponse;

public interface QuoteService {
    CreateQuoteResponseDto createQuote(CreateQuoteRequest hotelRequest);

   getQuotationReqestList getQuotations();

    QuotationResponse getQuatationById(long id);

  void updateQuote(long id , CreateQuoteRequest hotelRequest);
}
