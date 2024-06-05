package com.nvhien.repository;

import com.nvhien.db.DBConnector;
import com.nvhien.entity.Hotel;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Log4j2
public class HotelRepository implements IHotelRepository {
    private final Connection connection = DBConnector.getConnection();

    @Inject
    public HotelRepository() {
    }

    @Override
    public List<Hotel> findByLocation(String location, int offset, int limit) {
        List<Hotel> hotels = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM hotels WHERE MATCH(address) AGAINST ('");
            sb.append(location);
            sb.append("' IN BOOLEAN MODE) ORDER BY MATCH(address) AGAINST ('");
            sb.append(location);
            sb.append("' IN BOOLEAN MODE) DESC");
            sb.append(" LIMIT ");
            sb.append(limit);
            sb.append(" OFFSET ");
            sb.append(offset);
            log.info("Execute query: {}", sb.toString());
            ResultSet resultSet = statement.executeQuery(sb.toString());

            while (resultSet.next()) {
                Hotel hotel = Hotel.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone_number"))
                        .build();
                hotels.add(hotel);
            }
            return hotels;
        } catch (Exception exception) {
            log.error(exception);
            return null;
        }
    }
}
