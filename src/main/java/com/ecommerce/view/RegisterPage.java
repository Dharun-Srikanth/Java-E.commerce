package com.ecommerce.view;

import com.ecommerce.utils.StringUtil;

import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class RegisterPage {

    public void registerFormDesign() {
        println(StringUtil.DESIGN);
        print("\n");
        println(StringUtil.DISPLAY_CENTER + "--- * REGISTER PAGE * ---");
        print("\n");
    }

    public void printRegistrationSuccessful() {
        println("\n" + StringUtil.DESIGN+"\n");
        println(StringUtil.DISPLAY_CENTER + "*****************************");
        println(StringUtil.DISPLAY_CENTER + "* "+StringUtil.REGISTRATION_SUCCESSFUL+" *");
        println(StringUtil.DISPLAY_CENTER + "*****************************");
        println("\n" + StringUtil.DESIGN+"\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void passwordMismatch() {
        println("\n" + StringUtil.DESIGN+"\n");
        println(StringUtil.DISPLAY_CENTER + "************************");
        println(StringUtil.DISPLAY_CENTER + "* "+StringUtil.PASSWORD_MISMATCH+" *");
        println(StringUtil.DISPLAY_CENTER + "************************");
        println("\n" + StringUtil.DESIGN+"\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
