package com.ecommerce.controller;

import com.ecommerce.controller.implement.IOrderController;
import com.ecommerce.models.CartProduct;
import com.ecommerce.models.User;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.view.CartPage;
import com.ecommerce.view.HomePage;
import com.ecommerce.view.OrdersPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static com.ecommerce.controller.CartController.getCheckCartFile;
import static com.ecommerce.controller.CartController.setCartProductList;
import static com.ecommerce.controller.HomeController.handleNavigation;
import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.FileUtil.getFilePath;
import static com.ecommerce.utils.UsersUtil.getLoggedInUser;
import static com.ecommerce.utils.Utils.println;
import static com.ecommerce.view.CartPage.getTotal;
import static com.ecommerce.view.CartPage.setTotal;

public class OrderController implements IOrderController {
    private static OrdersPage ordersPage;

    public OrderController() {
        ordersPage = new OrdersPage();
    }

    @Override
    public void checkout() {
        User currentUser = getLoggedInUser();
        ArrayList<String[]> cartFile = getCheckCartFile();

        try {
            FileWriter orderWriter = new FileWriter(getFilePath() + "orders" + currentUser.getId() + ".csv");
            orderWriter.write("Product,Quantity,Price\n");
            double total = getTotal();
            for (String[] cartProduct : cartFile) {
                if (!cartProduct[0].equals("Product ID")) {
                    orderWriter.write(cartProduct[1] + ",x" + cartProduct[2] + ",₹" + Double.parseDouble(cartProduct[3]) * Integer.parseInt(cartProduct[2]) + "\n");
                    orderWriter.flush();
                }
            }
            orderWriter.write("Total - ₹" + total);
            orderWriter.close();
        } catch (IOException e) {
            new AppException(e.getMessage());
        }

        getLoggedInUser().setUserCart(null);
        setCartProductList(null);
        setTotal(0);
        String path = getFilePath() + "cart" + currentUser.getId() + ".csv";
        try {
            FileWriter cartWriter = new FileWriter(path);
            cartWriter.write("");
            cartWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ordersPage.loadOrdersPage();
    }

    public static void readOrdersFile() {
        Scanner read = null;
        String total = "0";
        try {
            read = new Scanner(new File(getFilePath() + "orders" + getLoggedInUser().getId() + ".csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (read.hasNext()) {
            String orders = read.nextLine();
            if (!orders.startsWith("Total")) {
                String[] orderList = orders.split(",");
                ordersPage.orderBoxDesign(orderList);
            } else {
                total = orders;
            }

        }
        println("\t\t\t\t\t\t\t\t\t\t**************************************************************************");
        println(StringUtil.DISPLAY_CENTER + "\t\t\t" + total);
        println("\t\t\t\t\t\t\t\t\t\t**************************************************************************\n");
        handleNavigation();
    }

    public static void orderOptions() {
        try {
            int choice = enterInt(StringUtil.DISPLAY_CENTER + "Enter your choice [-1 or 0]: ");
            if (choice == 0) {
                handleNavigation();
            } else if (choice == -1) {
                new HomePage().loadHomepage();
                handleNavigation();
            } else {
                invalidInput(new AppException(StringUtil.DISPLAY_CENTER + StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidInput(e);
        }
    }

    private static void invalidInput(AppException exp) {
        println(StringUtil.DISPLAY_CENTER + exp.getMessage());
        orderOptions();
    }


}
