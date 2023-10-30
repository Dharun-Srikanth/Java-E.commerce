package com.ecommerce.view;

import com.ecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.ecommerce.controller.ProductController.readProductFile;
import static com.ecommerce.utils.LoadUtils.navBar;
import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class ProductPage {

    public void loadProductPage(){
        navBar(StringUtil.PRODUCT_MENU);
        println("");
        println("\t[-1. Back to Homepage]"+"\t\t\t\t\t\t\t( Note: Press '0' to access 'Nav Options' (or) '1' to 'Choose Product' )\n");
    }

    public void loadProducts() {
        println("\tPRODUCTS:\n");
        readProductFile();
    }

    public void productBoxDesign(String[] products) {
        println("\t" + StringUtil.STAR_DESIGN);
        print("\t*");
        for (String value : products) {
            if (products[0].equals(value)) {
                int idLen = 8;
                String val = "  " + value;
                idLen -= val.length()-2;
                print(val);
                for(int i=0;i<idLen;i++){
                    print(" ");
                }
                print("||");
            } else if (products[1].equals(value)) {
                int titleLen = 30;
                String val = "\t"+value;
                titleLen -= val.length()-2;
                print(val);
                for(int i=0;i<titleLen;i++){
                    print(" ");
                }
                print("||");
            }else if (products[3].equals(value)){
                int priceLen = 20;
                String val = "\t"+value;
                priceLen -= val.length()-2;
                print(val);
                for(int i=0;i<priceLen;i++){
                    print(" ");
                }
                print("||");
            }else if (products[2].equals(value)){
                int descLen = 35;
                String val = "\t"+value;
                descLen -= val.length()-2;
                print(val);
                for(int i=0;i<descLen;i++){
                    print(" ");
                }
                print("||");
            }else if (products[4].equals(value)){
                int stocksLen = 15;
                String val = "\t"+value;
                stocksLen -= val.length()-2;
                print(val);
                for(int i=0;i<stocksLen;i++){
                    print(" ");
                }
                print("||");
            }else if (products[5].equals(value)){
                int btnLen = 20;
                String val;
                if(products[5].equals("Choose to Add")){
                    val = "\t  "+value;
                }else{
                    val = "\t  [ Add to Cart ]";
                }

                btnLen -= val.length()-2;
                print(val);
                for(int i=0;i<btnLen;i++){
                    print(" ");
                }
                print("  *\n");
            }

        }
    }

}
