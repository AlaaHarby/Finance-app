package com.android.alaa.financeapp.controllers;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Alaa on 1/12/2015.
 * Category Controller class holds list of predefined categories.
 * Inner category class holds name and thumbnail.
 */

class Category{
    String mName;
    String mImagePath;

    public Category(String mName, String mImagePath){
        this.mName = mName;
        this.mImagePath = mImagePath;
    }

    public String getName() {
        return mName;
    }

    public String getImagePath() {
        return mImagePath;
    }
}
public class CategoryController {

    HashMap<String, Category> mCategories;

    public Collection<Category> getCategories() {
        return mCategories.values();
    }

    public void addCategory(String name, String imagePath){
        Category newCat = new Category(name, imagePath);
        mCategories.put(name, newCat);
    }

}
