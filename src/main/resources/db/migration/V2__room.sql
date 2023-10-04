CREATE TABLE OMS.room_booking (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  hotel_id BIGINT,
                                  room_type_code VARCHAR(255),
                                  rate_plan_code VARCHAR(255),
                                  adults_count VARCHAR(255),
                                  children_count VARCHAR(255),
                                  promo_code VARCHAR(255),
                                  FOREIGN KEY (hotel_id) REFERENCES OMS.hotel_booking(id)
);