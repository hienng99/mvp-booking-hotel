package com.nvhien.repository;

import com.nvhien.db.DBConnector;
import com.nvhien.entity.HotelDTO;
import com.nvhien.entity.PaginationObj;
import com.nvhien.util.MHBUtil;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Log4j2
public class HotelRepository implements IHotelRepository {
    private final Connection connection = DBConnector.getConnection();

    @Inject
    public HotelRepository() {
    }

    @Override
    public PaginationObj<HotelDTO> findByLocation(String location, int offset, int limit) {
        List<HotelDTO> hotels = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM hotels WHERE address LIKE '%");
            sb.append(location);
            sb.append("%'");
            log.info("Execute query: {}", sb.toString());
            ResultSet resultSet = statement.executeQuery(sb.toString());
            int totalRecords = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                HotelDTO hotel = HotelDTO.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();
                hotel.setMatchingLocationProb(MHBUtil.getStringMatching(location, hotel.getAddress()));
                hotels.add(hotel);
            }

            List<HotelDTO> sortedHotels = hotels.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            if (offset >= sortedHotels.size()) {
                return PaginationObj.<HotelDTO>builder()
                        .total(totalRecords)
                        .rows(new ArrayList<>())
                        .build();
            }
            return PaginationObj.<HotelDTO>builder()
                    .total(totalRecords)
                    .rows(sortedHotels.subList(offset, Math.min(offset + limit, totalRecords)))
                    .build();
        } catch (Exception exception) {
            log.error("Error when query hotel by location.", exception);
            return null;
        }
    }
}
