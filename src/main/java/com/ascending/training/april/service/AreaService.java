package com.ascending.training.april.service;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.repository.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaDao areaDao;

    public boolean save(Area area){
        return areaDao.save(area);
    }
    public int updateAreaName(long areaId, String areaName){
        return areaDao.updateAreaName(areaId, areaName);
    }
    public boolean update(Area area){return areaDao.update(area);}
    public int delete(String areaName, String areaLocation){
        return areaDao.delete(areaName, areaLocation);
    }
    public List<Area> getAreas(){
        return areaDao.getAreas();
    }
    public Area getAreaByName(String areaName){
        return areaDao.getAreaByName(areaName);
    }
}
