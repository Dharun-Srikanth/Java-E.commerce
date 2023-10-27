package com.ecommerce.utils;

import com.ecommerce.models.User;

import java.util.ArrayList;

public class UsersUtil {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        UsersUtil.loggedInUser = loggedInUser;
    }
}
