package com.ecommerce.controller;

import com.ecommerce.controller.implement.IHomeController;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.view.CartPage;
import com.ecommerce.view.HomePage;
import com.ecommerce.view.OrdersPage;
import com.ecommerce.view.ProductPage;

import static com.ecommerce.controller.AuthController.logout;
import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.LoadUtils.navBar;
import static com.ecommerce.utils.UsersUtil.getLoggedInUser;
import static com.ecommerce.utils.Utils.println;

public class HomeController implements IHomeController {

    private static HomePage homePage;
    private static ProductPage productPage;
    private static CartPage cartPage;
    private static OrdersPage ordersPage;

    public HomeController() {
        homePage = new HomePage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        ordersPage = new OrdersPage();
    }

    public void printMenu() {
        homePage.loadHomepage();
    }

    public static void handleNavigation() {
        try {
            int choice = enterInt(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_NAV_CHOICE);
            println("\n" + StringUtil.DESIGN);
            if (choice == 0) {

            } else if (choice == 1) {
                productPage.loadProductPage();
                productPage.loadProducts();
            } else if (choice == 2) {
                cartPage.loadCartPage();
            } else if (choice == 3) {
                ordersPage.loadOrdersPage();
            } else if (choice == 4) {
                logout();
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }
}
