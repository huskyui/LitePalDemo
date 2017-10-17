package com.example.android.litepaltest;

import org.litepal.crud.DataSupport;

/**
 * Created by husky on 17-10-17.
 */

public class Category extends DataSupport{//注意extends了DataSupport
    private int id;
    private String categoryName;
    private int categoryCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
