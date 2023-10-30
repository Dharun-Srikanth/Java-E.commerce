package com.ecommerce.controller;

import com.ecommerce.controller.implement.ICategoryController;
import com.ecommerce.models.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.ecommerce.utils.FileUtil.getFilePath;
import static com.ecommerce.utils.LoadUtils.getCategories;

public class CategoryController implements ICategoryController {
    @Override
    public void printMenu() {

    }

    public void addCategoryFile() {
        File file = new File(getFilePath() + "categories.csv");

        ArrayList<Category> category = getCategories();

        try {
            FileWriter categoryWriter = new FileWriter(file);
            for (Category c : category) {
                categoryWriter.append(c.getId() + "," + c.getCategoryName() + "\n");
                categoryWriter.flush();
            }
            categoryWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void readCategoryFile() {
        Scanner read = null;
        try {
            read = new Scanner(new File(getFilePath() + "categories.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (read.hasNext()) {
            String category = read.next().trim();
            String[] categoryList = category.split(",");
        }
    }
}
