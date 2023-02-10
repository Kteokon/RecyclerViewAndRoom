package com.example.recyclerviewandroom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {
    @PrimaryKey
    @NonNull
    private int _id;
    @NonNull
    private String name, category;
    @NonNull
    private float price;

    @Ignore
    public Product(@NonNull String name, @NonNull String category, @NonNull float price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(int _id, @NonNull String name, @NonNull String category, @NonNull float price) {
        this._id = _id;
        this.name = name;
        this.category = category;
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

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return this.category;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return this.price;
    }
}