package com.example.repository.service.impl;

import com.example.exceptions.ServerException;
import com.example.repository.common.ErrorCode;
import com.example.repository.model.HotelBooking;
import com.example.repository.model.RoomBooking;
import com.example.repository.HotelBookingRepo;
import com.example.repository.RoomBookingRepo;
import com.example.repository.request.HotelBookingRequest;
import com.example.repository.request.HotelRequest;
import com.example.repository.request.RoomRequest;
import com.example.repository.response.*;
import com.example.repository.service.QuoteService;
import com.example.util.QuoteHelper;
import com.example.validation.QuoteValidator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import org.hibernate.Hibernate;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class QuoteServiceImpl implements QuoteService {

    private static final Logger logger = Logger.getLogger(QuoteServiceImpl.class.getName());
    private final QuoteHelper quoteHelper;
    private final HotelBookingRepo hotelBookingRepo;
    private final RoomBookingRepo roomBooking;

    public QuoteServiceImpl(QuoteHelper quoteHelper, HotelBookingRepo quoteRepository, RoomBookingRepo roomBooking) {
        this.quoteHelper = quoteHelper;
        this.hotelBookingRepo = quoteRepository;
        this.roomBooking = roomBooking;
    }


    @Transactional
    @Override
    public HotelResponse createQuote(HotelRequest hotelRequest) {
        HotelBooking hotel = null;
        try {
            hotel = saveHotelBookings(hotelRequest, hotelRequest.getRequestRooms());
        } catch (Exception e) {
            logger.info("An error occurred while processing the request - {}");
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return HotelResponse.builder()
                .quotationId(hotel.getId())
                .build();
    }

    @Transactional
    public HotelBooking saveHotelBookings(HotelRequest hotelRequest, List<RoomRequest> roomRequests) {
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
    public HotelBookingResponse getQuotations() {
        List<HotelBookingRequest> hotelBookings = hotelBookingRepo.getQuatationList();
        List<HotelBookingResponse> bookingResponses = new ArrayList<>();
        for (HotelBookingRequest booking : hotelBookings) {
            HotelBookingResponse response = getBookingResponse(booking);
            bookingResponses.add(response);
        }
        return errorResponse(bookingResponses);
    }

    private HotelBookingResponse errorResponse(List<HotelBookingResponse> bookingResponses) {
        if (bookingResponses.isEmpty()) {
            quoteHelper.createErrorResponse();
        }
        BaseResponse baseResponse = new BaseResponse<>(true, bookingResponses, null);
        return new HotelBookingResponse(baseResponse);


//        return HttpResponse.ok(new BaseResponse<>(true, bookingResponses, null));
    }

    private static HotelBookingResponse getBookingResponse(HotelBookingRequest booking) {
       return HotelBookingResponse.builder()
                .id(booking.getId())
                .dateCheckIn(booking.getDateCheckIn())
                .dateCheckOut(booking.getDateCheckOut())
                .status(booking.getStatus())
                .reference(booking.getQuotationReference())
                .build();
    }

    @Transactional
    @Override
    public HttpResponse<BaseResponse> getQuatationById(long id) {

        @NonNull Optional<HotelBooking> quotation = hotelBookingRepo.findById(id);
        if (quotation.isPresent()) {
            HotelBooking booking = quotation.get();
            Hibernate.initialize(booking.getRoomBookings());
        QuotationResponse quotationResponse= createQuotationData(booking);
            Map<String, QuotationResponse> data = Collections.singletonMap("quotation", quotationResponse);
            return quoteHelper.createSuccessResponse(data);
        }
        return quoteHelper.createErrorResponse();

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


    @Override
    public HttpResponse<BaseResponse> updateQuote(long id, HotelRequest hotelRequest) {
        boolean checkRequest = QuoteValidator.checkRequest(hotelRequest);
        if (!checkRequest) {
            logger.log(Level.ALL, "Request Validation Failed");
            return quoteHelper.createErrorResponse();
        }
        Optional<HotelBooking> existingQuotation = hotelBookingRepo.findById(id);
        if (existingQuotation.isEmpty()) {
            return quoteHelper.createErrorResponse();
        }
        HotelBooking hotelRequestToUpdate = getHotelBooking(hotelRequest, existingQuotation);

        // Call the updateHotelBooking method with id and updated entity
        hotelBookingRepo.updateHotelBooking(id, hotelRequestToUpdate.getHotelCode(), hotelRequestToUpdate.getQuotationReference(),
                hotelRequestToUpdate.getDateCheckIn(),hotelRequestToUpdate.getDateCheckOut(),hotelRequestToUpdate.getRatePlanCode());
        return quoteHelper.createSuccessResponseForUpdate();
    }

    private static HotelBooking getHotelBooking(HotelRequest hotelRequest, Optional<HotelBooking> existingQuotation) {
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
