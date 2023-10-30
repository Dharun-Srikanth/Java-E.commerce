package com.ecommerce.view;

import com.ecommerce.utils.StringUtil;

import static com.ecommerce.controller.CartController.readProductFile;
import static com.ecommerce.controller.OrderController.readOrdersFile;
import static com.ecommerce.utils.LoadUtils.navBar;
import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class OrdersPage {
    public void loadOrdersPage() {
        navBar(StringUtil.ORDER_MENU);
        println("\t[-1. Back to Homepage]\n");
        println("\n"+StringUtil.DISPLAY_CENTER+"\t\tMY ORDERS\n");
        println("\t\t\t\t\t\t\t\t\t\t**************************************************************************");
        readOrdersFile();
    }

    public void orderBoxDesign(String[] orderList) {
        print("\t\t\t\t\t\t\t\t\t\t*");
        for (String value : orderList) {
            if (orderList[0].equals(value)) {
                int prodLen = 20;
                String val = "\t" + value;
                prodLen -= val.length()-2;
                print(val);
                for(int i=0;i<prodLen;i++){
                    print(" ");
                }
                print("||");
            } else if (orderList[1].equals(value)) {
                int quantityLen = 20;
                String val = "\t"+value;
                quantityLen -= val.length()-2;
                print(val);
                for(int i=0;i<quantityLen;i++){
                    print(" ");
                }
                print("||");
            }else if (orderList[2].equals(value)){
                int priceLen = 20;
                String val = "\t"+value;
                priceLen -= val.length()-2;
                print(val);
                for(int i=0;i<priceLen;i++){
                    print(" ");
                }
                print("*");
            }
        }
        println("");
    }
}
