package com.example.repository.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Serdeable
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "hotel_booking",schema = "OMS")
public class HotelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "hotelBooking", targetEntity = RoomBooking.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Nullable
    private List<RoomBooking> roomBookings;

    @Column(name = "quotation_reference")
    private String quotationReference;

    @Column(name = "hotel_code")
    private String hotelCode;

    @Column(name = "date_check_in")
    private String dateCheckIn;

    @Column(name = "date_check_out")
    private String dateCheckOut;

    @Column(name = "rate_plan_code")
    private String ratePlanCode;

    @Column(name = "total_amount_before_tax")
    private String totalAmountBeforeTax;

    @Column(name = "total_amount_after_tax")
    private String totalAmountAfterTax;

    @Column(name = "total_tax_amount")
    private String totalTaxAmount;

    @Column(name = "is_active")
    private Boolean isActive=true;

    @Column(name = "status")
    private String status;
}