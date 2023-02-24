package com.example.recyclerviewandroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import java.util.List;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM product")
    LiveData<List<Product>> selectAllProducts();

    @Transaction
    @Query("SELECT * FROM category")
    LiveData<List<CategoryWithProducts>> selectAllCategoryWithProducts();

    @Transaction
    @Query("SELECT * FROM product")
    LiveData<List<ProductWithCategory>> selectAllProductWithCategory();

    @Query("SELECT * FROM category")
    LiveData<List<Category>> selectAllCategories();

    @Query("SELECT * FROM product WHERE product_id=:id")
    Product findProductById(int id);

    @Query("SELECT * FROM category WHERE category_id=:id")
    Category findCategoryById(int id);

    @Query("SELECT * FROM product WHERE name=:name")
    Product findProductByName(String name);

    @Query("SELECT * FROM category WHERE name=:name")
    Category findCategoryByName(String name);

    @Insert
    void insert(Product... products);

    @Insert
    void insert(Category... categories);

    @Update
    void update(Product... products);

    @Update
    void update(Category... categories);

    @Delete
    void delete(Product... products);

    @Delete
    void delete(Category... categories);

//    @Query("SELECT * FROM category WHERE name=:name")
//    int findByName(String name);
//
//    @Insert
//    void insertProduct(Product product);
}
