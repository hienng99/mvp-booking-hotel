package com.nvhien.repository;

import com.nvhien.constant.MHBConst;
import com.nvhien.db.DBConnector;
import com.nvhien.entity.Booking;
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
    public int create(Booking booking) {
        try {
            String checkinTime = MHBConst.SIMPLE_DATE_FORMAT.format(booking.getCheckinTime());
            String checkoutTime = MHBConst.SIMPLE_DATE_FORMAT.format(booking.getCheckoutTime());
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO bookings (customer_id, hotel_id, checkin_time, checkout_time, total_amount) VALUES (");
            sb.append(booking.getCustomerId());
            sb.append(", ");
            sb.append(booking.getHotelId());
            sb.append(", STR_TO_DATE('");
            sb.append(checkinTime);
            sb.append("', '%m/%d/%Y %H:%i:%s')");
            sb.append(", STR_TO_DATE('");
            sb.append(checkoutTime);
            sb.append("', '%m/%d/%Y %H:%i:%s')");
            sb.append(", ");
            sb.append(booking.getTotalAmount());
            sb.append(")");
            log.warn("Execute sql: {}", sb.toString());
            return statement.executeUpdate(sb.toString());
        } catch (SQLException e) {
            log.error(e);
            return 0;
        }
    }

    @Override
    public Booking getById(long id) {
        try {
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM bookings WHERE id = ");
            sb.append(id);
            sb.append(";");
            ResultSet resultSet = statement.executeQuery(sb.toString());
            if (resultSet.next()) {
                return Booking.builder()
                        .id(resultSet.getLong("id"))
                        .customerId(resultSet.getInt("customer_id"))
                        .hotelId(resultSet.getInt("hotel_id"))
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
