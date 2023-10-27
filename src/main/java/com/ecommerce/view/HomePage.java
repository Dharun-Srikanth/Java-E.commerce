package com.ecommerce.view;

import com.ecommerce.utils.StringUtil;

import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class HomePage {

    public void navBar(){
        println(StringUtil.DESIGN);
        print("\t"+StringUtil.LOGO);
        print("\t\t\t\t\t\t\t\t\t\t\t\t\t"+StringUtil.SEARCH);
        print("\t\t\t\t\t\t\t\t\t\t\t"+StringUtil.AFTER_LOGIN_MENU+"\n");
        println(StringUtil.DESIGN);
    }
}
