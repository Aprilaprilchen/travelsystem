package com.ascending.training.april.jdbc;

import com.ascending.training.april.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    static final String DB_URL = "jdbc:postgresql://localhost:5434/travel_db";
    static final String USER = "admin";
    static final String PASS = "travel123!";

    public List<Hotel> getHotels() {
        List<Hotel> hotels = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
//            Creat a connection
            System.out.println("Connecting to Database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

//            Excute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM hotel";
            rs = stmt.executeQuery(sql);

//            Extract data from result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                int price = rs.getInt("price");
                int comfort_level = rs.getInt("comfort_level");
                int area_id = rs.getInt("area_id");

                Hotel hotel = new Hotel();
                hotel.setId(id);
                hotel.setName(name);
                hotel.setLocation(location);
                hotel.setPrice(price);
                hotel.setComfort_level(comfort_level);
                hotels.add(hotel);
            }
        }

        catch (Exception e) {
            e.printStackTrace();

            logger.info("This is an exception");
        }

        finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        logger.warn("This is a message");

        return hotels;
    }
//    public static void main(String[] args) {
//        HotelDao hotelDao = new HotelDao();
//        List<Hotel> hotels = hotelDao.getHotels();
//
//        for (Hotel hotel : hotels) {
//        System.out.println(hotel.getName());
//        }
//    }
}
