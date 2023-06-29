package service.impl;

import java.util.List;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import java.util.ArrayList;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDaoImpl();

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void edit(Product newProduct) {
        Product oldProduct = productDao.get(Integer.parseInt(newProduct.getId()));
        oldProduct.setName(newProduct.getName());
        oldProduct.setCatalog_id(newProduct.getCatalog_id());
        oldProduct.setStatus(newProduct.getStatus());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setContent(newProduct.getContent());
        oldProduct.setDiscount(newProduct.getDiscount());
        oldProduct.setImage_link(newProduct.getImage_link());
        oldProduct.setCreated(newProduct.getCreated());
        oldProduct.setSold(newProduct.getSold());
        oldProduct.setInventory(newProduct.getInventory());
        productDao.edit(oldProduct);
    }

    @Override
    public void delete(String id) {
        productDao.delete(id);

    }

    @Override
    public Product get(int id) {
        return productDao.get(id);
    }

    @Override
    public Product get(String name) {
        return productDao.get(name);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> searchByName(String productName) {
        return productDao.searchByName(productName);
    }

    @Override
    public List<Product> getProductByIdCate(int idCate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> checkProductNew() {
        return productDao.checkProductNew();
    }

    @Override
    public List<Product> checkProductSale() {
        return productDao.checkProductSale();
    }

    @Override
    public int getTotalProduct() {
        return productDao.getTotalProduct();
    }

    @Override
    public List<Product> pagingpProducts(int i) {
        return productDao.pagingProducts(i);
    }

    @Override
    public int getTotalProduct(int i) {
        return productDao.getTotalProduct(i);
    }

    @Override
    public List<Product> pagingpProducts(int i, int id) {
        return productDao.pagingProducts(i, id);
    }

    @Override
    public List<Product> productSale() {
        return productDao.productSale();
    }

    @Override
    public List<Product> productNew() {
        return productDao.productNew();
    }

    @Override
    public void addProductSale(Product product) {
         productDao.addProductSale(product);
    }

    @Override
    public void addProductNew(Product product) {
        productDao.addProductNew(product);
    }

    @Override
    public void updateListSale() {
        productDao.updateListSale();
    }

    @Override
    public void updateListNew() {
        productDao.updateListNew();
    }

    @Override
    public void addProductSelling(Product product) {
        productDao.addProductSelling(product);
    }

    @Override
    public void updateListSelling() {
        productDao.updateListSelling();
    }

    @Override
    public List<Product> pagingProductsNew(int index) {
        return productDao.pagingProductsNew(index);
    }

    @Override
    public List<Product> pagingProductsSale(int index) {
        return productDao.pagingProductsSale(index);
    }

    @Override
    public int getTotalProductNew() {
        return productDao.getTotalProductNew();
    }

    @Override
    public int getTotalProductSale() {
        return productDao.getTotalProductSale();
    }

    @Override
    public void editSold(long sold, int id) {
        productDao.editSold(sold,id);
    }

    @Override
    public List<Product> pagingProductsSelling(int index) {
        return productDao.pagingProductsSelling(index);
    }

    @Override
    public int getTotalProductSelling() {
        return productDao.getTotalProductSelling();
    }

}
