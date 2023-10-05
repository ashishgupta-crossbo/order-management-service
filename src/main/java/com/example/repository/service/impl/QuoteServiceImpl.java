package com.example.repository.service.impl;

import com.example.exceptions.CustomException;
import com.example.exceptions.ServerException;
import com.example.repository.common.ErrorCode;
import com.example.repository.model.HotelBooking;
import com.example.repository.model.RoomBooking;
import com.example.repository.HotelBookingRepo;
import com.example.repository.RoomBookingRepo;
import com.example.repository.request.GetQuotationRequest;
import com.example.repository.request.CreateQuoteRequest;
import com.example.repository.request.RoomRequest;
import com.example.repository.response.*;
import com.example.repository.service.QuoteService;
import com.example.validation.QuoteValidator;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import org.hibernate.Hibernate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class QuoteServiceImpl implements QuoteService {

    private static final Logger logger = Logger.getLogger(QuoteServiceImpl.class.getName());
    private final HotelBookingRepo hotelBookingRepo;
    private final RoomBookingRepo roomBooking;

    public QuoteServiceImpl(HotelBookingRepo quoteRepository, RoomBookingRepo roomBooking) {
        this.hotelBookingRepo = quoteRepository;
        this.roomBooking = roomBooking;
    }


    @Transactional
    @Override
    public CreateQuoteResponseDto createQuote(CreateQuoteRequest createQuoteRequest) {
        HotelBooking hotel;
        try {
            hotel = saveHotelBookings(createQuoteRequest, createQuoteRequest.getRequestRooms());
        } catch (Exception e) {
            logger.info("An error occurred while processing the request - {}");
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return CreateQuoteResponseDto.builder()
                .quotationId(hotel.getId())
                .build();
    }

    @Transactional
    public HotelBooking saveHotelBookings(CreateQuoteRequest hotelRequest, List<RoomRequest> roomRequests) {
        HotelBooking hotel = HotelBooking.builder()
                .hotelCode(hotelRequest.getHotelCode())
                .quotationReference(hotelRequest.getQuotationReference())
                .dateCheckIn(hotelRequest.getDateCheckIn())
                .dateCheckOut(hotelRequest.getDateCheckOut())
                .ratePlanCode(hotelRequest.getRatePlanCode())
                .totalAmountBeforeTax(hotelRequest.getTotalAmountBeforeTax())
                .totalAmountAfterTax(hotelRequest.getTotalAmountAfterTax())
                .totalTaxAmount(hotelRequest.getTotalTaxAmount())
                .isActive(true)
                .status("OPEN")
                .build();
        hotelBookingRepo.save(hotel);
        List<RoomBooking> roomBookings = new ArrayList<>();
        for (RoomRequest roomRequest : roomRequests) {
            RoomBooking room = RoomBooking.builder()
                    .roomTypeCode(roomRequest.getRoomTypeCode())
                    .ratePlanCode(roomRequest.getRatePlanCode())
                    .adultCount(roomRequest.getAdultCode())
                    .childrenCount(roomRequest.getChildrenCount())
                    .promoCode(roomRequest.getPromoCode())
                    .hotelBooking(hotel)
                    .build();
            roomBookings.add(room);
        }
        hotel.setRoomBookings(roomBookings);
        roomBooking.saveAll(roomBookings);
        return hotel;
    }

    @Override
    public getQuotationReqestList getQuotations() {
        List<GetQuotationRequest> hotelBookings = hotelBookingRepo.getQuatationList();
        List<GetQuotationResponse> quotationResponses = new ArrayList<>();

        for (GetQuotationRequest booking : hotelBookings) {
            GetQuotationResponse response = getAllQuotations(booking);
            quotationResponses.add(response);
        }
        return new getQuotationReqestList(quotationResponses);
    }

    private static GetQuotationResponse getAllQuotations(GetQuotationRequest booking) {
       return GetQuotationResponse.builder()
                .id(booking.getId())
                .dateCheckIn(booking.getDateCheckIn())
                .dateCheckOut(booking.getDateCheckOut())
                .status(booking.getStatus())
                .reference(booking.getQuotationReference())
                .build();
    }

    @Transactional
    @Override
    public QuotationResponse getQuatationById(long id) {

        Optional<HotelBooking> quotation = hotelBookingRepo.findById(id);
        if (quotation.isPresent()) {
            HotelBooking booking = quotation.get();
            Hibernate.initialize(booking.getRoomBookings());
            return createQuotationData(booking);
        }else {
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }

    private QuotationResponse createQuotationData(HotelBooking booking) {
        List<RoomBookingDTO> roomBookingDTOs = new ArrayList<>();

        assert booking.getRoomBookings() != null;
        for (RoomBooking roomBooking : booking.getRoomBookings()) {
            RoomBookingDTO roomBookingDTO = RoomBookingDTO.builder()
                    .roomTypeCode(roomBooking.getRoomTypeCode())
                    .promoCode(roomBooking.getPromoCode())
                    .adultCount(roomBooking.getAdultCount())
                    .childrenCount(roomBooking.getChildrenCount())
                    .ratePlanCode(roomBooking.getRatePlanCode())
                    .build();
            roomBookingDTOs.add(roomBookingDTO);
        }

        return QuotationResponse.builder()
                .hotelCode(booking.getHotelCode())
                .dateCheckIn(booking.getDateCheckIn())
                .dateCheckOut(booking.getDateCheckOut())
                .quotationRef(booking.getQuotationReference())
                .ratePlanCode(booking.getRatePlanCode())
                .totalAmountBeforeTax(booking.getTotalAmountBeforeTax())
                .totalAmountAfterTax(booking.getTotalAmountAfterTax())
                .totalTaxAmount(booking.getTotalTaxAmount())
                .reqRooms(roomBookingDTOs)
                .build();

    }

    @Transactional
    @Override
    public void updateQuote(long id, CreateQuoteRequest hotelRequest) {
        Optional<HotelBooking> existingQuotation = hotelBookingRepo.findById(id);
        if (existingQuotation.isEmpty()) {
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
        HotelBooking hotelRequestToUpdate = getHotelBooking(hotelRequest, existingQuotation);
        hotelBookingRepo.updateHotelBooking(id, hotelRequestToUpdate.getHotelCode(), hotelRequestToUpdate.getQuotationReference(),
                hotelRequestToUpdate.getDateCheckIn(),hotelRequestToUpdate.getDateCheckOut(),hotelRequestToUpdate.getRatePlanCode());
    }

    private static HotelBooking getHotelBooking(CreateQuoteRequest hotelRequest, Optional<HotelBooking> existingQuotation) {
        return HotelBooking.builder()
                .hotelCode(hotelRequest.getHotelCode())
                .quotationReference(hotelRequest.getQuotationReference())
                .dateCheckIn(hotelRequest.getDateCheckIn())
                .dateCheckOut(hotelRequest.getDateCheckOut())
                .ratePlanCode(hotelRequest.getRatePlanCode())
                .roomBookings(existingQuotation.get().getRoomBookings())
                .build();
    }
}
