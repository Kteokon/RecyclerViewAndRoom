package com.example.recyclerviewandroom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {
    @PrimaryKey
    @NonNull
    int _id;
    @NonNull
    String name, category;
    @NonNull
    float price;

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

    public void setName(String name) {
        this.name = name;
    }
}