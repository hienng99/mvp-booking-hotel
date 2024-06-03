package com.nvhien.service;

import com.nvhien.entity.Booking;
import com.nvhien.entity.BookingDAO;
import com.nvhien.entity.BookingRequest;
import com.nvhien.repository.BookingRepository;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.Date;

@Log4j2
@Singleton
public class BookingService implements IBookingService {
    private final BookingRepository repository;

    @Inject
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public boolean create(BookingRequest bookingRequest) {
        BookingDAO bookingDAO = BookingDAO.builder()
                .customerId(bookingRequest.getCustomerId())
                .hotelId(bookingRequest.getHotelId())
                .checkinTime(new Date(bookingRequest.getCheckinTime()))
                .checkoutTime(new Date(bookingRequest.getCheckoutTime()))
                .build();
        try {
            repository.create(bookingDAO);
            log.info("Booking created");
            return true;
        } catch (SQLException sqlException) {
            log.error(sqlException);
            return false;
        }
    }
}
