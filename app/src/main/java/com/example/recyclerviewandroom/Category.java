package com.example.recyclerviewandroom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    int _id;

    @NonNull
    String name;

    public Category(@NonNull String name) {
        this.name = name;
    }
}
