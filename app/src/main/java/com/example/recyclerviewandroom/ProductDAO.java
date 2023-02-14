package com.example.recyclerviewandroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM product ORDER BY name")
    LiveData<List<Product>> selectAll();

    @Query("SELECT * FROM product WHERE _id=:id")
    Product findById(int id);

    @Insert
    void insert(Product... products);

    @Update
    void update(Product... products);

    @Delete
    void delete(Product... products);
}
