package com.example.recyclerviewandroom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "product",
        foreignKeys = {@ForeignKey(entity = Category.class,
                parentColumns = "_id",
                childColumns = "category_id",
                onDelete = ForeignKey.CASCADE)
        })
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int _id;
    @NonNull
    private String name;
    @NonNull
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @NonNull
    private float price;

    public Product(@NonNull String name, @NonNull int categoryId, @NonNull float price) {
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    public void setId(int id) {
        this._id = id;
    }
    public int getId() {
        return this._id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public int getCategoryId() {
        return this.categoryId;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return this.price;
    }
}