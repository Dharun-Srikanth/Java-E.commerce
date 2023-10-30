package com.ecommerce.view;

import com.ecommerce.utils.StringUtil;

import static com.ecommerce.controller.HomeController.handleNavigation;
import static com.ecommerce.utils.LoadUtils.navBar;
import static com.ecommerce.utils.UsersUtil.getLoggedInUser;
import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class HomePage {

    public void loadHomepage(){
        navBar(StringUtil.HOME_MENU);
        println("\n"+StringUtil.DISPLAY_CENTER+"Welcome "+getLoggedInUser().getName()+"\n");
        handleNavigation();
    }
}
