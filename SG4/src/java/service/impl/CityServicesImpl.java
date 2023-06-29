/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.CityDao;
import dao.impl.CityDaoImpl;
import java.util.List;
import model.City;
import service.CityService;

/**
 *
 * @author Admin
 */
public class CityServicesImpl implements CityService {
    
    CityDao cityDao = new CityDaoImpl();

    @Override
    public List<City> getAll() {
        return cityDao.getAll();
    }
    
}
