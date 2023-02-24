package com.example.recyclerviewandroom;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProductWithCategory {
    @Embedded
    Product products;
    @Relation(
            parentColumn = "category_id",
            entityColumn = "category_id"
    )
    public Category category;
}
