package com.example.recyclerviewandroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

//import com.example.recyclerviewandroom.relations.CategoryWithProducts;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM category ORDER BY name")
    LiveData<List<Category>> selectAll();

    @Query("SELECT * FROM category WHERE _id=:id")
    Category findById(int id);

    @Query("SELECT * FROM category WHERE name=:name")
    int findByName(String name);

    @Insert
    void insert(Category... categories);

    @Insert
    void insertProduct(Product product);

    @Delete
    void delete(Category... categories);

    @Update
    void update(Category... categories);
}
