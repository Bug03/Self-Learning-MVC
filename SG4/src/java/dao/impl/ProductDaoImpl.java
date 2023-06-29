package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;

import model.Product;

import dao.ProductDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.connectDB;

public class ProductDaoImpl extends connectDB implements ProductDao {

    @Override
    public void insert(Product product) {
        String sql = "INSERT INTO product(catalog_id, name, price, status, description, content, discount, image_link, created,sold,inventory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getCatalog_id());
            ps.setString(2, product.getName());
            ps.setString(3, product.getPrice());
            ps.setString(4, product.getStatus());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getContent());
            ps.setString(7, product.getDiscount());
            ps.setString(8, product.getImage_link());
            ps.setString(9, product.getCreated());
            ps.setLong(10, product.getSold());
            ps.setInt(11, product.getInventory());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Product product) {
        String sql = "UPDATE product SET name = ?, catalog_id = ?, price = ?, status = ?, description = ?, content = ?, discount = ?, image_link = ?, created = ?, sold = ?, inventory = ? WHERE id = ?";
        new connectDB();
        Connection con = connectDB.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getCatalog_id());
            ps.setString(3, product.getPrice());
            ps.setString(4, product.getStatus());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getContent());
            ps.setString(7, product.getDiscount());
            ps.setString(8, product.getImage_link());
            ps.setString(9, product.getCreated());
            ps.setLong(10, product.getSold());
            ps.setInt(11, product.getInventory());
            ps.setString(12, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM product WHERE id = ?";
        new connectDB();
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product get(int id) {
        String sql = "SELECT * FROM product WHERE id = ? ";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product get(String name) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductById(int id) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product WHERE catalog_id=?";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> searchByName(String keyword) {
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM product WHERE name LIKE ? ";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                productList.add(product);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> checkProductNew() {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product WHERE image_link IN (select image_link from product where catalog_id=?)";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> checkProductSale() {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product WHERE image_link IN (select image_link from product where catalog_id=?)";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 7);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int getTotalProduct() {
        String sql = "SELECT COUNT(id) FROM product where catalog_id != 7";
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
    public List<Product> pagingProducts(int index) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product where catalog_id != 7 "
                + "ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public int getTotalProduct(int i) {
        String sql = "SELECT COUNT(id) FROM product where catalog_id = " + i;
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
    public List<Product> pagingProducts(int index, int id) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product where catalog_id = " + id + " ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public List<Product> productSale() {
        List<Product> products = new ArrayList<Product>();
        String sql = "select * from product where discount != 0";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> productNew() {
        List<Product> products = new ArrayList<Product>();
        String sql = "DECLARE @Year int = Year(getDate()), @Month int = Month(getDate()), @Day int = DAY(getdate());\n" +
                     "select * from product where DATEDIFF(day,created,DATEFROMPARTS (@Year, @Month, @Day)) < 7";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void addProductSale(Product product) {
        String sql = "INSERT INTO product(catalog_id, name, price, status, description, content, discount, image_link, created, sold, inventory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "7");
            ps.setString(2, product.getName());
            ps.setString(3, product.getPrice());
            ps.setString(4, product.getStatus());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getContent());
            ps.setString(7, product.getDiscount());
            ps.setString(8, product.getImage_link());
            ps.setString(9, product.getCreated());
            ps.setLong(10, product.getSold());
            ps.setInt(11, product.getInventory());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductNew(Product product) {
        String sql = "INSERT INTO product(catalog_id, name, price, status, description, content, discount, image_link, created, sold, inventory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "5");
            ps.setString(2, product.getName());
            ps.setString(3, product.getPrice());
            ps.setString(4, product.getStatus());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getContent());
            ps.setString(7, product.getDiscount());
            ps.setString(8, product.getImage_link());
            ps.setString(9, product.getCreated());
            ps.setLong(10, product.getSold());
            ps.setInt(11, product.getInventory());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductSelling(Product product) {
        String sql = "INSERT INTO product(catalog_id, name, price, status, description, content, discount, image_link, created, sold,inventory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        new connectDB();
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "6");
            ps.setString(2, product.getName());
            ps.setString(3, product.getPrice());
            ps.setString(4, product.getStatus());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getContent());
            ps.setString(7, product.getDiscount());
            ps.setString(8, product.getImage_link());
            ps.setString(9, product.getCreated());
            ps.setLong(10, product.getSold());
            ps.setInt(11, product.getInventory());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateListNew() {
        String sql = "delete from product where catalog_id = 5";
        new connectDB();
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateListSale() {
        String sql = "delete from product where catalog_id = 7";
        new connectDB();
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateListSelling() {
        String sql = "delete from product where catalog_id = 6";
        new connectDB();
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> pagingProductsNew(int index) {
        List<Product> products = new ArrayList<Product>();
        String sql = "DECLARE @Year int = Year(getDate()), @Month int = Month(getDate()), @Day int = DAY(getdate());\n" +
                     "select * from product where DATEDIFF(day,created,DATEFROMPARTS (@Year, @Month, @Day)) < 7 "
                +    "ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public List<Product> pagingProductsSale(int index) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product where discount != 0 ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public int getTotalProductSale() {
        String sql = "SELECT count(id) FROM product where discount != 0";
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
    public int getTotalProductNew() {
        String sql = "SELECT count(id) FROM product where DAY(GETDATE()) - DAY(created) < 8 and MONTH(GETDATE()) - MONTH(created) = 0\n"
                + "and YEAR(GETDATE()) - YEAR(created) = 0";
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
    public void editSold(long sold, int id) {
        String sql = "UPDATE product SET sold = ? WHERE id = ?";
        new connectDB();
        Connection con = connectDB.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, sold);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> pagingProductsSelling(int index) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product where sold >= 100 ORDER BY id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setCatalog_id(rs.getString("catalog_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setStatus(rs.getString("status"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                product.setDiscount(rs.getString("discount"));
                product.setImage_link(rs.getString("image_link"));
                product.setCreated(rs.getString("created"));
                product.setSold(rs.getLong("sold"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public int getTotalProductSelling() {
        String sql = "select count(id) from product where sold > 100";
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
}
