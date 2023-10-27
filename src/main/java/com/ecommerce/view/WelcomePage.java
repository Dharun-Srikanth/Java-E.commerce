package com.ecommerce.view;

import com.ecommerce.utils.StringUtil;

import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class WelcomePage {

    public void welcome() {
        try {
            println(StringUtil.DESIGN);
            println("\n"+StringUtil.DISPLAY_CENTER+StringUtil.WELCOME_MESSAGE);
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public void navBar() {
        println(StringUtil.DESIGN);
        print("\t"+StringUtil.LOGO);
        print(StringUtil.DISPLAY_END+StringUtil.AUTH_MENU+"\n");
        println(StringUtil.DESIGN);
    }

    public void printAuthMenu(){
        println("\n");
        println(StringUtil.DISPLAY_CENTER+StringUtil.BEFORE_LOGIN_MSG);
    }
}
