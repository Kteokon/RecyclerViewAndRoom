package com.example.recyclerviewandroom;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface ProductDAO {
    @Query("SELECT * FROM product ORDER BY name")
    List<Product> selectAll();

    @Query("SELECT * FROM product WHERE _id=:id")
    Product findById(int id);

    @Query("SELECT * FROM product WHERE category=:category")
    List<Product> findByCategory(String category);

    @Insert
    void insert(Product... products);

    @Update
    void update(Product... products);

    @Delete
    void delete(Product... products);
}
