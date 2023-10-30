package com.ecommerce.controller;

import com.ecommerce.controller.implement.ICartController;
import com.ecommerce.models.Cart;
import com.ecommerce.models.CartProduct;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.view.CartPage;
import com.ecommerce.view.HomePage;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.ecommerce.controller.HomeController.handleNavigation;
import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.AppInput.enterString;
import static com.ecommerce.utils.FileUtil.getFilePath;
import static com.ecommerce.utils.LoadUtils.getProducts;
import static com.ecommerce.utils.UsersUtil.getLoggedInUser;
import static com.ecommerce.utils.UsersUtil.setLoggedInUser;
import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;
import static com.ecommerce.view.CartPage.getTotal;
import static com.ecommerce.view.CartPage.setTotal;

public class CartController implements ICartController {

    private static final ArrayList<Product> products = getProducts();
    private static String[] cartProductList = null;
    private static ArrayList<String[]> checkCartFile = new ArrayList<>();

    private static boolean check = false;
    private static CartPage cartPage;
    private static HomePage homePage;
    private static OrderController orderController;

    public CartController() {
        cartPage = new CartPage();
        homePage = new HomePage();
        orderController = new OrderController();
    }

    @Override
    public void printCart() {

    }

    @Override
    public void addToCart() {
        try {
            int choice = enterInt(StringUtil.DISPLAY_CENTER + "Enter your choice [0 or 1]: ");
            if (choice == 0) {
                handleNavigation();
            } else if (choice == 1) {
                chooseProduct();
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

    public static void chooseProduct() {
        User currentUser = getLoggedInUser();
        Cart cart = currentUser.getUserCart();
        try {
            int productChoice = enterInt(StringUtil.DISPLAY_CENTER + "Enter product number to add: ");
            Product product = null;
            for (Product prod : products) {
                if (productChoice == prod.getId()) {
                    product = prod;
                    break;
                }
            }
            if (cart != null) {
                boolean isFound = false;
                for (CartProduct cartProd : cart.getCartProducts()) {
                    if (cartProd.getProduct().getId() == productChoice) {
                        cartProd.setCount(cartProd.getCount() + 1);
                        isFound = true;
                    }
                }
                if (!isFound) {
                    cart.getCartProducts().add(new CartProduct(product, 1));
                }
                cart.setCartProducts(cart.getCartProducts());
                currentUser.setUserCart(cart);
            } else {
                cart = new Cart();
                ArrayList<CartProduct> cartProducts = new ArrayList<>();
                cartProducts.add(new CartProduct(product, 1));
                cart.setCartProducts(cartProducts);
                currentUser.setUserCart(cart);
            }
            setLoggedInUser(currentUser);
            addUserCartFile();
            String ans = enterString(StringUtil.DISPLAY_CENTER + "Do you want to add more (Y/N): ");
            if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("yes")) {
                chooseProduct();
            } else if (ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("no")) {
                println(StringUtil.DISPLAY_CENTER + "Your products has been added to your cart!");
                new CartController().addToCart();
            } else {
                invalidInput(new AppException(StringUtil.DISPLAY_CENTER + StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidInput(e);
        }
    }

    public static void addUserCartFile() {
        String path = getFilePath() + "cart" + getLoggedInUser().getId() + ".csv";
        File file = new File(path);

        try {
            FileWriter userCartWriter = new FileWriter(file);
            FileWriter userCartWriter2 = new FileWriter(file, true);

            userCartWriter.write("Product ID,Product,Quantity,Price\n");
            userCartWriter.close();

            if (file.exists() && check) {
                File temp = new File(getFilePath() + "temp" + getLoggedInUser().getId() + ".csv");
                FileChannel src = new FileInputStream(file).getChannel();
                FileChannel dest = new FileOutputStream(temp).getChannel();
                dest.transferFrom(src, 0, src.size());

                FileWriter fileWriter = new FileWriter(temp, true);
                for (CartProduct cartProduct : getLoggedInUser().getUserCart().getCartProducts()) {
                    Product product = cartProduct.getProduct();
                    fileWriter.write(product.getId() + "," + product.getTitle() + "," + cartProduct.getCount() + "," + product.getPrice() + "\n");
                    fileWriter.flush();
                }
                FileChannel src1 = new FileInputStream(temp).getChannel();
                FileChannel dest1 = new FileOutputStream(file).getChannel();
                dest1.transferFrom(src1, 0, src1.size());
                fileWriter.close();
                userCartWriter2.close();



            } else {
                check = true;
                for (CartProduct cartProduct : getLoggedInUser().getUserCart().getCartProducts()) {
                    Product product = cartProduct.getProduct();
                    userCartWriter2.write(product.getId() + "," + product.getTitle() + "," + cartProduct.getCount() + "," + product.getPrice() + "\n");
                    userCartWriter2.flush();
                }
                userCartWriter2.close();
            }
        } catch (IOException e) {
            new AppException(e.getMessage());
        }
    }

    public static void readProductFile() {
        double total = 0;

        Scanner read = null;
        try {
            String path = getFilePath() + "cart" + getLoggedInUser().getId() + ".csv";
            read = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            new AppException(e.getMessage());
        }
        if(read != null){
            while (read.hasNext()) {
                String userCart = read.nextLine();
                cartProductList = userCart.split(",");
                checkCartFile.add(cartProductList);
                if (!userCart.startsWith("Product ID")) {
                    total += (Integer.parseInt(cartProductList[2]) * Double.parseDouble(cartProductList[3]));
                    checkCartFile.add(cartProductList);
                }
                cartPage.cartBoxDesign(cartProductList);
            }
        }

        setTotal(total);
        if (cartProductList == null) {
            println("\n" + StringUtil.DISPLAY_CENTER + "Your cart is empty. Add some products!!\n");
            println("\t" + StringUtil.STAR_DESIGN);
        } else {
            println("\t" + StringUtil.STAR_DESIGN);
            println(StringUtil.DISPLAY_END + "\t \t[ '#' TO CHECKOUT - ₹" + getTotal() + " ]");
        }
        checkoutOptions();
    }

    public static void checkoutOptions() {

        String choice = enterString(StringUtil.DISPLAY_CENTER + "Enter your choice [0 or #]: ");
        if (choice.equalsIgnoreCase("#")) {
            if (cartProductList != null)
                orderController.checkout();
            else {
                println(StringUtil.DISPLAY_CENTER + "* Your Cart is Empty - Add some products to continue *\n");
                checkoutOptions();
            }
        } else if (choice.equalsIgnoreCase("0")) {
            handleNavigation();
        } else if (choice.equalsIgnoreCase("-1")) {
            homePage.loadHomepage();
        } else {
            invalidInput(new AppException(StringUtil.INVALID_CHOICE));
        }
        println("\n" + StringUtil.DESIGN);
    }

    private static void invalidInput(AppException exp) {
        println(StringUtil.DISPLAY_CENTER + exp.getMessage());
        new CartController().addToCart();
    }

    public static ArrayList<String[]> getCheckCartFile() {
        return checkCartFile;
    }

    public static String[] getCartProductList() {
        return cartProductList;
    }


    public static void setCartProductList(String[] cartProductList) {
        CartController.cartProductList = cartProductList;
    }
}
