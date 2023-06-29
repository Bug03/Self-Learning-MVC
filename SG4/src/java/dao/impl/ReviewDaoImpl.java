package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ReviewDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.connectDB;
import model.Review;

public class ReviewDaoImpl extends connectDB implements ReviewDao {

    @Override
    public void insert(Review review) {
        String sql = "INSERT INTO review(product_id,  name, email, content, created) VALUES (?, ?, ?, ?, ?)";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, review.getProduct_id());
            ps.setString(2, review.getName());
            ps.setString(3, review.getEmail());
            ps.setString(4, review.getContent());
            ps.setString(5, review.getCreated());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Review review) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM review WHERE id=?";
        Connection con = connectDB.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Review get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Review get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Review> getAll() {
        List<Review> reviews = new ArrayList<Review>();
        String sql = "SELECT * FROM review";
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getString("id"));
                review.setName(rs.getString("name"));
                review.setEmail(rs.getString("email"));
                review.setProduct_id(rs.getString("product_id"));
                review.setContent(rs.getString("content"));
                review.setCreated(rs.getString("created"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<Review> getReviewById(int id) {
        List<Review> reviews = new ArrayList<Review>();
        String sql = "SELECT * FROM review WHERE product_id=?";
        Connection con = connectDB.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getString("id"));
                review.setName(rs.getString("name"));
                review.setEmail(rs.getString("email"));
                review.setProduct_id(rs.getString("product_id"));
                review.setContent(rs.getString("content"));
                review.setCreated(rs.getString("created"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public int getTotalReview() {
        String sql = "SELECT COUNT(id) FROM review";
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
    public List<Review> paging(int index) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM review ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getString("id"));
                review.setName(rs.getString("name"));
                review.setEmail(rs.getString("email"));
                review.setProduct_id(rs.getString("product_id"));
                review.setContent(rs.getString("content"));
                review.setCreated(rs.getString("created"));
                reviews.add(review);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reviews;
    }

}
