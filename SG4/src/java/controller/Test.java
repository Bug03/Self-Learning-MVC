/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Ordered;
import model.Product;
import model.Transactions;
import model.User;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.UserService;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;
import service.impl.UserServicesImpl;

/**
 *
 * @author Admin
 */
public class Test {
    private static final long serialVersionUID = 1L;
    static TransactionService transactionService = new TransactionServicesImpl();
    static OrderedService catalogService = new OrderedServiceImpl();
    static ProductService productService = new ProductServiceImpl();
    static UserService userService = new UserServicesImpl();
    public static void main(String[] args) {
       
    }
}
