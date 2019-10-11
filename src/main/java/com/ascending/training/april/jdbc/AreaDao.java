package com.ascending.training.april.jdbc;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.sun.rowset.internal.InsertRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AreaDao{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    static final String DB_URL = System.getProperty("database.url");
    static final String USER = System.getProperty("database.user");
    static final String PASS = System.getProperty("database.password");

    public List<Area> getAreas() {
        logger.info("Enter the method getArea");

        List<Area> areas = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

         try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT * FROM areas";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int consumptionLevel = rs.getInt("consumption_level");
                String location = rs.getString("location");
                String description = rs.getString("description");

                Area area = new Area();
//                area.setId(id);
                area.setName(name);
                area.setConsumptionLevel(consumptionLevel);
                area.setLocation(location);
                area.setDescription(description);
                areas.add(area);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            //logger.info("This is message 1");
        } finally{
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

//        logger.trace("Trace - Area size = " + areas.size());
//        logger.debug(String.format("The area %s was inserted into the table.", areas.toString()));
//        logger.info(String.format("abs.", areas.toString()));
//        logger.warn("Trace - Area" + areas.size());
//        logger.error("Trace - Area" + areas.size());

        logger.info("Exist the method getArea");
        return areas;
    }

    public Area getAreaByName(String areaName){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Area area = new Area();

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT FROM areas WHERE name = '" + areaName + "'";
            rs = stmt.executeQuery(sql);

            long id = rs.getLong("id");
            String name = rs.getString("name");
            int consumptionLevel = rs.getInt("consumption_level");
            String location = rs.getString("location");
            String description = rs.getString("description");

            area.setName(name);
            area.setConsumptionLevel(consumptionLevel);
            area.setLocation(location);
            area.setDescription(description);

        }catch(SQLException e){
            e.printStackTrace();
        }

        return area;
    }

//    insert data
    public int insertAreas(Area areas) {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO areas(name, consumptionLevel, location, description) values ('"
                + areas.getName() + "'," + areas.getConsumptionLevel() + "," +
                    "'" + areas.getLocation() + "'," + "'" + areas.getDescription() + "'" + ")";
            result = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    update data
    public int updateArea(Area area){

        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            System.out.println("Connecting to databae...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE areas set name = '" + area.getName() + "'" + " where id = '" + area.getId() + "'";
            result = stmt.executeUpdate(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

//    delete data
    public int deleteAreaById(long areaId){
        CustomerDao customerDao = new CustomerDao();
        customerDao.deleteCustomerByAreaId(areaId);
        HotelDao hotelDao = new HotelDao();
        hotelDao.deleteHotelByAreaId(areaId);

        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM areas WHERE id = '" + areaId + "'";
            result = stmt.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }

        return result;
        }


//        原用来测试的main方法
    public static void main(String[] args) {
//        AreaDao areaDao = new AreaDao();
//        List<Area> areas = areaDao.getArea();
//
//        for (Area area : areas) {
//            System.out.println(area.getName());
//
//        }
    }
}
