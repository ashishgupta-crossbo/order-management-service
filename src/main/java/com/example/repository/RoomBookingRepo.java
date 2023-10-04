package com.example.repository;

import com.example.repository.model.RoomBooking;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface RoomBookingRepo extends CrudRepository<RoomBooking,Long> {

    RoomBooking save(@NonNull RoomBooking room);

}
