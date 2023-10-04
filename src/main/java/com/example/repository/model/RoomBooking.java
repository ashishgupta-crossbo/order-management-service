package com.example.repository.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Builder
@Entity
@Table(name = "room_booking",schema = "OMS")
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    @Nullable
    private HotelBooking hotelBooking;

    @Column(name = "room_type_code")
    private String roomTypeCode;

    @Column(name = "rate_plan_code")
    private String ratePlanCode;

    @Column(name = "adults_count")
    private String adultCount;

    @Column(name = "children_count")
    private String childrenCount;

    @Column(name = "promo_code")
    private String promoCode;



}
