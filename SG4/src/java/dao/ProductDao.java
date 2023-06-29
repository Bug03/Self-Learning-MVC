package dao;

import java.util.List;

import model.Product;

public interface ProductDao {

    void insert(Product product);

    void edit(Product product);

    void delete(String id);

    Product get(int id);

    Product get(String name);

    List<Product> getAll();

    List<Product> getProductById(int id);

    List<Product> searchByName(String productName);

    List<Product> productSale();

    List<Product> productNew();

    List<Product> checkProductNew();

    List<Product> checkProductSale();

    void addProductSale(Product product);

    void addProductNew(Product product);

    void addProductSelling(Product product);

    void updateListSale();

    void updateListNew();

    void updateListSelling();

    int getTotalProduct();

    int getTotalProduct(int i);

    List<Product> pagingProducts(int index);

    List<Product> pagingProducts(int index, int id);
    
    List<Product> pagingProductsSelling(int index);

    List<Product> pagingProductsNew(int index);

    List<Product> pagingProductsSale(int index);
    
    int getTotalProductNew();
    
    int getTotalProductSale();
    
    int getTotalProductSelling();
    
    void editSold(long sold, int id);
}
