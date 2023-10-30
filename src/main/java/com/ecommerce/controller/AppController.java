package com.ecommerce.controller;

import com.ecommerce.controller.implement.IAppController;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.view.HomePage;
import com.ecommerce.view.WelcomePage;

import java.io.IOException;

import static com.ecommerce.utils.AppInput.enterString;
import static com.ecommerce.utils.Utils.print;

public class AppController implements IAppController {
    private final AuthController authController;
    private final WelcomePage welcomePage;
    private final HomePage homePage;

    public AppController() {
        welcomePage = new WelcomePage();
        authController = new AuthController(this);
        homePage = new HomePage();
    }


    @Override
    public void init() {
        welcomePage.welcome();
        String choice = enterString(StringUtil.DISPLAY_CENTER+StringUtil.ENTRY_OPTION);
        print("\n");
        if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")){
            printAuthMenu();
        }else{
            init();
        }
    }

    @Override
    public void printAuthMenu() {
        welcomePage.navBar();
        welcomePage.printAuthMenu();
    }
}
