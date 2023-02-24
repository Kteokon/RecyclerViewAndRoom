package com.example.recyclerviewandroom;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithProducts {
    @Embedded public Category category;
    @Relation(
            parentColumn = "category_id",
            entityColumn = "category_id"
    )
    List<Product> products;
}
