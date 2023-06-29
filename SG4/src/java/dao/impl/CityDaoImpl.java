/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.CityDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.connectDB;
import model.City;

/**
 *
 * @author Admin
 */
public class CityDaoImpl extends connectDB implements CityDao {

    @Override
    public List<City> getAll() {
        List<City> citys = new ArrayList();
        String sql = "SELECT * FROM city";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setCityName(rs.getString("name"));
                System.out.println(city);
                citys.add(city);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return citys;
    }
}
