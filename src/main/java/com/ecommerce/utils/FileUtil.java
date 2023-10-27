package com.ecommerce.utils;

import java.io.File;

public class FileUtil {
    private static File credentialsFile;

    public static File getCredentialsFile(){
        if(credentialsFile == null){
            credentialsFile = new File("src/main/java/com/ecommerce/assets/credentials.csv");
        }
        return credentialsFile;
    }

    public static String getFilePath(){
        return "src/main/java/com/ecommerce/assets/";
    }
}
