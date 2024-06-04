package com.nvhien.service;

import com.nvhien.constant.MHBConst;
import com.nvhien.entity.Booking;
import com.nvhien.entity.BookingRequest;
import com.nvhien.entity.BookingResponse;
import com.nvhien.repository.BookingRepository;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;

@Log4j2
@Singleton
public class BookingService implements IBookingService {
    private final BookingRepository repository;

    @Inject
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(BookingRequest bookingRequest) {
        Booking booking = Booking.builder()
                .customerId(bookingRequest.getCustomerId())
                .hotelId(bookingRequest.getHotelId())
                .checkinTime(new Date(bookingRequest.getCheckinTime()))
                .checkoutTime(new Date(bookingRequest.getCheckoutTime()))
                .totalAmount(bookingRequest.getTotalAmount())
                .build();

        int resultCode = repository.create(booking);
        if (resultCode != 0) {
            log.info("Booking created successfully");
            return true;
        }
        log.info("Booking created fail");
        return false;
    }

    @Override
    public BookingResponse get(long id) {
        Booking booking = repository.getById(id);
        if (booking != null) {
            return BookingResponse.builder()
                    .id(booking.getId())
                    .customerId(booking.getCustomerId())
                    .hotelId(booking.getHotelId())
                    .checkinTime(MHBConst.SIMPLE_DATE_FORMAT.format(booking.getCheckinTime()))
                    .checkoutTime(MHBConst.SIMPLE_DATE_FORMAT.format(booking.getCheckoutTime()))
                    .totalAmount(booking.getTotalAmount())
                    .build();
        }
        return null;
    }
}
