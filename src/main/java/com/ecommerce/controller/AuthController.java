package com.ecommerce.controller;

import com.ecommerce.controller.implement.IAuthController;
import com.ecommerce.models.Role;
import com.ecommerce.models.User;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.AppInput;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.view.LoginPage;
import com.ecommerce.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.AppInput.enterString;
import static com.ecommerce.utils.FileUtil.getCredentialsFile;
import static com.ecommerce.utils.UsersUtil.setLoggedInUser;
import static com.ecommerce.utils.Utils.print;
import static com.ecommerce.utils.Utils.println;

public class AuthController implements IAuthController {
    private final HomeController homeController;
    private final AppController appController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    public AuthController(AppController appController) {
        this.appController = appController;
        homeController = new HomeController();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
    }

    @Override
    public void authMenu() {
        int choice = 0;
        try {
            choice = enterInt(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_CHOICE);
            print("\n");
            if (choice == 1)
                login();
            else if (choice == 2)
                register();
            else
                invalidInput(new AppException(StringUtil.INVALID_CHOICE));
        } catch (AppException exp) {
            invalidInput(exp);
        }
    }

    private void invalidInput(AppException exp) {
        println(exp.getMessage());
        authMenu();
    }

    @Override
    public void login() {
        String email, pass;
        loginPage.loginFormDesign();
        email = enterString(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_EMAIL);
        pass = enterString(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_PASSWORD);
        println("\n" + StringUtil.DESIGN);
        User user = validateUser(email, pass);
        if (user != null) {
            setLoggedInUser(user);
            loginPage.printLoginSuccessful();
            homeController.printMenu();
        } else {
            loginPage.invalidCredentials();
            println(StringUtil.DESIGN);
            authMenu();
        }
    }

    private User validateUser(String email, String pass) {
        try {
            Scanner credentials = new Scanner(getCredentialsFile());
            while (credentials.hasNext()) {
                String value = credentials.next().trim();
                if (!value.equals("id")) {
                    String[] usersValue = value.split(",");
                    if (usersValue[2].equals(email) && usersValue[3].equals(pass)) {
                        User user = new User();
                        user.setId(Integer.parseInt(usersValue[0]));
                        user.setName(usersValue[1]);
                        user.setEmail(usersValue[2]);
                        user.setPassword(usersValue[3]);
                        if (user.getEmail().equals("admin@admin.com"))
                            user.setRole(Role.ADMIN);
                        else
                            user.setRole(Role.USER);
                        return user;
                    }
                }
            }
            credentials.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void register() {
        String name, email, pass, c_pass;
        registerPage.registerFormDesign();
        name = enterString(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_NAME);
        email = enterString(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_EMAIL);
        pass = enterString(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_PASSWORD);
        c_pass = enterString(StringUtil.DISPLAY_CENTER + StringUtil.ENTER_PASSWORD_AGAIN);

        if (c_pass.equals(pass)) {
            try {
                FileWriter addCredentials = new FileWriter(getCredentialsFile(), true);
                int id = (int) (Math.random() * 100) + 1;
                addCredentials.append("\n");
                addCredentials.append(id + "," + name + "," + email + "," + pass);
                addCredentials.flush();
                addCredentials.close();
                registerPage.printRegistrationSuccessful();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            registerPage.passwordMismatch();
        }
        authMenu();
    }

    @Override
    public void logout() {

    }
}
