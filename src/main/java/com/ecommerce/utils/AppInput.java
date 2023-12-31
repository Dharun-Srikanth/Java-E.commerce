package com.ecommerce.utils;

import static com.ecommerce.utils.AppScanner.getScanner;

public class AppInput {
    public static String enterString(String msg){
        Utils.print(msg);
        return getScanner().nextLine();
    }

    public static int enterInt(String msg)throws AppException {
        Utils.print(msg);
        int input;
        try {
            input = Integer.parseInt(getScanner().nextLine());
        }catch (Exception exp){
            throw new AppException("Invalid input! Please enter a valid input");
        }
        return input;
    }
}
