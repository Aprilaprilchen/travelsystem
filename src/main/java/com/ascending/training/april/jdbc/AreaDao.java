package com.ascending.training.april.jdbc;

import com.ascending.training.april.model.Area;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AreaDao{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    static final String DB_URL = "jdbc:postgresql://localhost:5434/travel_db";
    static final String USER = "admin";
    static final String PASS = "travel123!";

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
            sql = "SELECT * FROM area";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int comsumption_level = rs.getInt("comsumption_level");
                String location = rs.getString("location");
                String description = rs.getString("description");

                Area area = new Area();
                area.setId(id);
                area.setName(name);
                area.setComsumption_level(comsumption_level);
                area.setLocation(location);
                area.setDescription(description);
                areas.add(area);
            }
        }

        catch (Exception e) {
            logger.error(e.getMessage());

            e.printStackTrace();

            //logger.info("This is message 1");
        }

        finally{
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        logger.trace("Trace - Area size = " + areas.size());
        logger.debug(String.format("The area %s was inserted into the table.", areas.toString()));
        logger.info(String.format("abs.", areas.toString()));
        logger.warn("Trace - Area" + areas.size());
        logger.error("Trace - Area" + areas.size());

        logger.info("Exist the method getArea");
        return areas;
    }

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
