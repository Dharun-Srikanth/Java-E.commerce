package com.ecommerce.controller;

import com.ecommerce.controller.implement.IProductController;
import com.ecommerce.models.Cart;
import com.ecommerce.models.CartProduct;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.view.HomePage;
import com.ecommerce.view.ProductPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.ecommerce.controller.HomeController.handleNavigation;
import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.AppInput.enterString;
import static com.ecommerce.utils.FileUtil.getFilePath;
import static com.ecommerce.utils.LoadUtils.getProducts;
import static com.ecommerce.utils.UsersUtil.getLoggedInUser;
import static com.ecommerce.utils.Utils.println;

public class ProductController implements IProductController {


    private static ProductPage productPage;
    private static CartController cartController;

    private static final ArrayList<Product> products = getProducts();

    public ProductController() {
        productPage = new ProductPage();
        cartController = new CartController();
    }

    @Override
    public void showProducts(int categoryId) {

    }

    public void addProductsFile() {
        File file = new File(getFilePath() + "products.csv");

        try {
            FileWriter productWriter = new FileWriter(file);
            productWriter.append("S.No.,Product,Description,Price,Stocks,Choose to Add\n");
            for (Product product : products) {
                productWriter.append(product.getId() + "," + product.getTitle() + "," + product.getDescription() + "," + product.getPrice() + "," + product.getStocks() + "," + product.getCategory().getCategoryName() + "\n");
                productWriter.flush();
            }
            productWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void readProductFile() {
        Scanner read = null;
        try {
            read = new Scanner(new File(getFilePath() + "products.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (read.hasNext()) {
            String category = read.nextLine();
            String[] productList = category.split(",");
            productPage.productBoxDesign(productList);
        }
        println("\t" + StringUtil.STAR_DESIGN + "\n");
        cartController.addToCart();
        println(StringUtil.DESIGN);
    }


}
