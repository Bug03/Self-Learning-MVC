package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

//import com.vienmv.dao.impl.String;
//import com.vienmv.model.Category;
//import com.vienmv.dao.impl.String;
//import com.vienmv.dao.impl.Override;
//import com.vienmv.dao.impl.String;
import java.sql.ResultSet;
import model.Catalog;
import model.Product;
import dao.CategoryDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.connectDB;

public class CategoryDaoImpl extends connectDB implements CategoryDao {

    @Override
    public void insert(Catalog category) {
        String sql = "INSERT INTO catalog(name,parent_id) VALUES (?, ?)";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getParent_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Catalog category) {
        String sql = "UPDATE catalog SET name = ?, parent_id = ? WHERE id = ?";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getParent_id());
            ps.setString(3, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override
    public Catalog get(int id) {
        String sql = "SELECT * FROM catalog WHERE id = ? ";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Catalog category = new Catalog();

                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                category.setParent_id(rs.getString("parent_id"));

                return category;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Catalog get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Catalog> getAll() {
        List<Catalog> categories = new ArrayList<Catalog>();
        String sql = "SELECT * FROM catalog";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Catalog category = new Catalog();

                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                category.setParent_id(rs.getString("parent_id"));
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public void delete(String id) {
        System.out.println("Id :" + id);
        String sql = "DELETE FROM catalog WHERE id = ?";
        new connectDB();
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Catalog> getCateByProduct(int id) {
        List<Catalog> products_cate = new ArrayList<Catalog>();
        String sql = "select catalog.id, catalog.name from catalog,product where catalog.id = product.catalog_id and product.id = ?";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Catalog catagory_product = new Catalog();
                catagory_product.setId(rs.getString("id"));
                catagory_product.setName(rs.getString("name"));
                products_cate.add(catagory_product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products_cate;
    }

    @Override
    public int getToltal() {
        String sql = "SELECT COUNT(id) FROM catalog";
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
    public List<Catalog> paging(int index) {
        List<Catalog> categories = new ArrayList<>();
        String sql = "SELECT * FROM catalog ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Catalog category = new Catalog();
                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                category.setParent_id(rs.getString("parent_id"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

}
