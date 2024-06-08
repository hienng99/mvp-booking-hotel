package com.nvhien.repository;

import com.nvhien.constant.MHBConst;
import com.nvhien.db.DBConnector;
import com.nvhien.entity.Booking;
import com.nvhien.entity.BookingDTO;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Log4j2
@Singleton
public class BookingRepository implements IBookingRepository {
    private final Connection connection = DBConnector.getConnection();

    @Inject
    public BookingRepository() {
    }

    @Override
    public int create(BookingDTO bookingDTO) {
        try {
            String checkinTime = MHBConst.SIMPLE_DATE_FORMAT.format(bookingDTO.getCheckinTime());
            String checkoutTime = MHBConst.SIMPLE_DATE_FORMAT.format(bookingDTO.getCheckoutTime());
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO bookings (customer_id, hotel_id, checkin_time, checkout_time, total_amount) VALUES (");
            sb.append(bookingDTO.getCustomerId());
            sb.append(", ");
            sb.append(bookingDTO.getHotelId());
            sb.append(", STR_TO_DATE('");
            sb.append(checkinTime);
            sb.append("', '%m/%d/%Y %H:%i:%s')");
            sb.append(", STR_TO_DATE('");
            sb.append(checkoutTime);
            sb.append("', '%m/%d/%Y %H:%i:%s')");
            sb.append(", ");
            sb.append(bookingDTO.getTotalAmount());
            sb.append(")");
            log.warn("Execute sql: {}", sb.toString());
            return statement.executeUpdate(sb.toString());
        } catch (SQLException e) {
            log.error(e);
            return 0;
        }
    }

    @Override
    public BookingDTO getById(long id) {
        try {
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT b.*, h.name as hotel_name, h.address as hotel_address, h.phone as hotel_phone FROM bookings b JOIN hotels h ON b.hotel_id = h.id WHERE b.id = ");
            sb.append(id);
            sb.append(";");
            ResultSet resultSet = statement.executeQuery(sb.toString());
            if (resultSet.next()) {
                return BookingDTO.builder()
                        .id(resultSet.getLong("id"))
                        .customerId(resultSet.getInt("customer_id"))
                        .hotelName(resultSet.getString("hotel_name"))
                        .hotelAddress(resultSet.getString("hotel_address"))
                        .hotelPhone(resultSet.getString("hotel_phone"))
                        .checkinTime(new Date(resultSet.getTimestamp("checkin_time").getTime()))
                        .checkoutTime(new Date(resultSet.getTimestamp("checkout_time").getTime()))
                        .totalAmount(resultSet.getDouble("total_amount"))
                        .build();
            }
            return null;
        } catch (SQLException e) {
            log.error(e);
            return null;
        }
    }
}
