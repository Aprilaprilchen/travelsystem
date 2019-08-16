package com.ascending.training.april.repository;

        import com.ascending.training.april.model.Area;

        import java.util.List;

public interface AreaDao {
    boolean save(Area area);
    int updateAreaName(long areaId, String areaName);
    int delete(String areaName, String areaLocation);
    List<Area> getAreas();
    Area getAreaByName(String areaName);
}
