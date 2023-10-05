package com.example.repository;

import com.example.repository.model.HotelBooking;
import com.example.repository.request.GetQuotationRequest;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface HotelBookingRepo extends CrudRepository<HotelBooking, Long> {

    HotelBooking save(@NonNull HotelBooking hotel);

    @Query(value = "SELECT hb.id, hb.quotation_reference, hb.date_check_in, hb.date_check_out, hb.status FROM hotel_booking hb")
    List<GetQuotationRequest> getQuatationList();

    @Join(value = "roomBookings", type = Join.Type.FETCH)
    @NonNull
    Optional<HotelBooking> findById(Long id);

    @Transactional
    @Query(value = "UPDATE hotel_booking " +
            "SET " +
            "hotel_code = :hotelCode, " +
            "quotation_reference = :quotationReference, " +
            "date_check_in = :dateCheckIn, " +
            "date_check_out = :dateCheckOut, " +
            "rate_plan_code = :ratePlanCode " +
            "WHERE id = :id")
    int updateHotelBooking(
            Long id,
            String hotelCode,
            String quotationReference,
            String dateCheckIn,
            String dateCheckOut,
            String ratePlanCode
    );
}