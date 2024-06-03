package com.nvhien.repository;

import com.nvhien.db.DBConnector;
import com.nvhien.entity.BookingDAO;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

@Log4j2
@Singleton
public class BookingRepository implements IBookingRepository {
    private final Connection connection = DBConnector.getConnection();

    @Inject
    public BookingRepository() {
    }

    @Override
    public int create(BookingDAO bookingDAO) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String checkinTime = sdf.format(bookingDAO.getCheckinTime());
        String checkoutTime = sdf.format(bookingDAO.getCheckoutTime());
        Statement statement = connection.createStatement();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO bookings (customer_id, hotel_id, checkin_time, checkout_time) VALUES (");
        sb.append(bookingDAO.getCustomerId());
        sb.append(", ");
        sb.append(bookingDAO.getHotelId());
        sb.append(", STR_TO_DATE('");
        sb.append(checkinTime);
        sb.append("', '%m/%d/%Y %H:%i:%s')");
        sb.append(", STR_TO_DATE('");
        sb.append(checkoutTime);
        sb.append("', '%m/%d/%Y %H:%i:%s'))");
        log.warn("Execute sql: {}", sb.toString());
        return statement.executeUpdate(sb.toString());
    }
}
