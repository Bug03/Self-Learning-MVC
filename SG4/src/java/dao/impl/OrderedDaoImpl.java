package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderedDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.connectDB;
import model.Ordered;

public class OrderedDaoImpl implements OrderedDao {

    @Override
    public void insert(Ordered ordered) {
        String sql = "INSERT INTO ordered(product_id, transaction_id,qty) VALUES (?, ?, ?)";
        Connection con = connectDB.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(ordered.getProduct_id()));
            ps.setInt(2, Integer.parseInt(ordered.getTransaction_id()));
            ps.setInt(3, ordered.getQty());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Ordered ordered) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Ordered get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ordered get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Ordered> getAll() {
        List<Ordered> ordereds = new ArrayList<Ordered>();
        String sql = "SELECT * FROM ordered";
        Connection con = connectDB.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ordered ordered = new Ordered();
                ordered.setId(rs.getString("id"));
                ordered.setProduct_id(rs.getString("product_id"));
                ordered.setTransacsion_id(rs.getString("transaction_id"));
                ordered.setQty(Integer.parseInt(rs.getString("qty")));
                ordereds.add(ordered);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordereds;
    }

    @Override
    public int getTotalOrdered(String departmentId) {
        String sql;
        if (departmentId.equals("")) {
            sql = "SELECT COUNT(id) FROM ordered";
        } else {
            sql = "SELECT COUNT(ordered.id) FROM ordered JOIN transactions "
                    + "ON transactions.id = ordered.transaction_id WHERE transactions.department_id = " + departmentId;
        }
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Ordered> paging(int index, String departmentId) {
        List<Ordered> ordereds = new ArrayList<>();
        String sql;
        if (departmentId.equals("")) {
            sql = "SELECT * FROM ordered ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        } else{
            sql = "SELECT * FROM ordered JOIN transactions ON transactions.id = ordered.transaction_id "
                    + "WHERE transactions.department_id = "+departmentId+" ORDER BY ordered.id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        }
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ordered ordered = new Ordered();
                ordered.setId(rs.getString("id"));
                ordered.setProduct_id(rs.getString("product_id"));
                ordered.setTransacsion_id(rs.getString("transaction_id"));
                ordered.setQty(Integer.parseInt(rs.getString("qty")));
                ordereds.add(ordered);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordereds;
    }

}
