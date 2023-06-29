package service;

import model.Product;
import java.util.List;

public interface ProductService {

    void insert(Product product);

    void edit(Product product);

    void delete(String id);

    Product get(int id);

    Product get(String name);

    List<Product> getAll();

    List<Product> getProductById(int id);

    List<Product> searchByName(String productName);

    List<Product> getProductByIdCate(int idCate);

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

    List<Product> pagingpProducts(int i);

    List<Product> pagingpProducts(int i, int id);

    List<Product> pagingProductsNew(int index);

    List<Product> pagingProductsSale(int index);
    
    List<Product> pagingProductsSelling(int index);
    
    int getTotalProductNew();
    
    int getTotalProductSale();
    
    int getTotalProductSelling();
    
    void editSold(long sold, int id);

}
