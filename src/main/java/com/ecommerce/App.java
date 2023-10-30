package com.ecommerce;

import com.ecommerce.controller.AppController;
import com.ecommerce.utils.LoadUtils;

public class App {
    public static void main(String[] args) {
        AppController appController = new AppController();
        LoadUtils loadUtils = new LoadUtils();
        loadUtils.load();
        appController.init();
    }
}
