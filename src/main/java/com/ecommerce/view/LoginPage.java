package com.ecommerce.view;

import com.ecommerce.utils.StringUtil;

import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class LoginPage {

    public void printLoginSuccessful() {
        println("\n" + StringUtil.DESIGN+"\n");
        println(StringUtil.DISPLAY_CENTER + "********************");
        println(StringUtil.DISPLAY_CENTER + "* "+StringUtil.LOGIN_SUCCESSFUL+" *");
        println(StringUtil.DISPLAY_CENTER + "********************");
        println("\n" + StringUtil.DESIGN+"\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void invalidCredentials() {
        println(StringUtil.DISPLAY_CENTER+"*************************");
        println(StringUtil.DISPLAY_CENTER+"* "+StringUtil.INVALID_CREDENTIALS+" *");
        println(StringUtil.DISPLAY_CENTER+"*************************");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginFormDesign(){
        println(StringUtil.DESIGN);
        print("\n");
        println(StringUtil.DISPLAY_CENTER+"--- * LOGIN PAGE * ---");
        print("\n");
    }
}
