package com.ecommerce.controller;

import com.ecommerce.controller.implement.IHomeController;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.utils.UsersUtil;
import com.ecommerce.view.HomePage;

import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.UsersUtil.getLoggedInUser;
import static com.ecommerce.utils.Utils.println;

public class HomeController implements IHomeController {

    public HomeController() {
    }

    public void printMenu() {
        new HomePage().navBar();
        println("\n"+StringUtil.DISPLAY_CENTER+"Welcome "+getLoggedInUser().getName()+"\n");
        handleNavigation();   
    }
    
    public void handleNavigation(){
        try {
            int choice = enterInt(StringUtil.ENTER_NAV_CHOICE);
            if(choice==0){
                
            } else if (choice==1) {
                
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }
}
