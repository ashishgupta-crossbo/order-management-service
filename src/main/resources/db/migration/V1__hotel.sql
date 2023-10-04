CREATE TABLE OMS.hotel_booking (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   quotation_reference VARCHAR(255),
                                   hotel_code VARCHAR(255),
                                   date_check_in VARCHAR(255),
                                   date_check_out VARCHAR(255),
                                   rate_plan_code VARCHAR(255),
                                   total_amount_before_tax VARCHAR(255),
                                   total_amount_after_tax VARCHAR(255),
                                   total_tax_amount VARCHAR(255),
                                   is_active BOOLEAN DEFAULT TRUE,
                                   status VARCHAR(255)
);