package com.ecommerce.utils;

import com.ecommerce.controller.CategoryController;
import com.ecommerce.controller.ProductController;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.ecommerce.utils.FileUtil.getFilePath;
import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class LoadUtils {
    public static CategoryController categoryController;
    public static ProductController productController;
    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();

    public LoadUtils() {
        categoryController = new CategoryController();
        productController = new ProductController();
    }

    public void load() {
        Category dress = new Category(1, "Dress");
        Category mobile = new Category(2, "Mobile");
        categories.add(dress);
        categories.add(mobile);

        Product tShirt = new Product(1, "T-Shirt", "Good T-Shirt", 299, 10, dress);
        Product shirt = new Product(2, "Shirt", "Design Shirt", 499, 10, dress);
        products.add(tShirt);
        products.add(shirt);

        Product iphone = new Product(3, "iPhone 15", "New Launched model", 120000, 10, mobile);
        Product samsung = new Product(4, "Samsung S23 Ultra", "Best processor mobile", 89000, 10, mobile);
        products.add(iphone);
        products.add(samsung);

        categoryController.addCategoryFile();
        productController.addProductsFile();


    }

    public static void navBar(String nav){
        println(StringUtil.DESIGN);
        print("\t"+StringUtil.LOGO);
        print("\t\t\t\t\t\t\t\t\t\t\t\t\t"+StringUtil.SEARCH);
        print("\t\t\t\t\t\t\t\t\t\t\t"+nav +"\n");
        println(StringUtil.DESIGN);
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }
}
