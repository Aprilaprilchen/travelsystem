package com.ascending.training.april.jdbc;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    static final String DB_URL = System.getProperty("database.url");
    static final String USER = System.getProperty("database.user");
    static final String PASS = System.getProperty("database.password");

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
            sql = "SELECT * FROM hotels";
            rs = stmt.executeQuery(sql);

//            Extract data from result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                BigDecimal price = rs.getBigDecimal("price");
                int comfortLevel = rs.getInt("comfort_level");
                int areaId = rs.getInt("area_id");

                Hotel hotel = new Hotel();
//                hotel.setId(id);
                hotel.setName(name);
                hotel.setLocation(location);
                hotel.setPrice(price);
                hotel.setComfortLevel(comfortLevel);
                hotels.add(hotel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("This is an exception");
        } finally {
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

    public int insertHotel(Hotel hotel){
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            int areaId = 1;

            sql = "INSERT INTO hotels(name, location, price, comfort_level, area_id) values ('"
                    + hotel.getName() + "','" + hotel.getLocation() + "','" + hotel.getPrice() + "','" +
                    hotel.getComfortLevel() + "','" + areaId + "')";
            result = stmt.executeUpdate(sql);

        }catch (SQLException e){
            logger.info("This is an exception");
            e.printStackTrace();
        }
        return result;
    }

    public int deleteHotelByAreaId(long areaId){

        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM hotels WHERE area_id = '" + areaId + "'";
            result = stmt.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }


    public int deleteHotelByName(String hotelName){

        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM hotels WHERE area_name = '" + hotelName + "'";
            result = stmt.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//        HotelDao hotelDao = new HotelDao();
//        List<Hotel> hotels = hotelDao.getHotels();
//
//        for (Hotel hotel : hotels) {
//        System.out.println(hotel.getName());
//        }
    }
}
