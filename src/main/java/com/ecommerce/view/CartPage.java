package com.ecommerce.view;

import com.ecommerce.controller.ProductController;
import com.ecommerce.utils.StringUtil;

import static com.ecommerce.controller.CartController.readProductFile;
import static com.ecommerce.utils.LoadUtils.navBar;
import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class CartPage {
    private static double total = 0;

    public void loadCartPage() {
        navBar(StringUtil.CART_MENU);
        println("\t[-1. Back to Homepage]" + "\t\t\t\t\t\t\t( Note: Press '0' to access 'Nav Options' (or) '#' to 'Checkout' )\n");
        println("\n" + StringUtil.DISPLAY_CENTER + "\t\tMY CART\n");
        println("\t" + StringUtil.STAR_DESIGN);
        readProductFile();
    }

    public void cartBoxDesign(String[] cartProductList) {
        print("\t*");
        for (String value : cartProductList) {
            if (cartProductList[0].equals(value)) {
                int idLen = 33;
                String val = "\t" + value;
                idLen -= val.length() - 2;
                print(val);
                for (int i = 0; i < idLen; i++) {
                    print(" ");
                }
                print("||");
            } else if (cartProductList[1].equals(value)) {
                int titleLen = 38;
                String val = "\t" + value;
                titleLen -= val.length() - 2;
                print(val);
                for (int i = 0; i < titleLen; i++) {
                    print(" ");
                }
                print("||");
            } else if (cartProductList[2].equals(value)) {
                int priceLen = 33;
                String val = "\t" + value;
                priceLen -= val.length() - 2;
                print(val);
                for (int i = 0; i < priceLen; i++) {
                    print(" ");
                }
                print("||");
            } else if (cartProductList[3].equals(value)) {
                int descLen = 31;
                String val = "\t" + value;
                descLen -= val.length() - 2;
                print(val);
                for (int i = 0; i < descLen; i++) {
                    print(" ");
                }
                print("*");
            }
        }
        println("");
    }

    public static double getTotal() {
        return total;
    }

    public static void setTotal(double total) {
        CartPage.total = total;
    }
}
