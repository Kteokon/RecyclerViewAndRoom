package com.example.recyclerviewandroom;

import android.app.Application;
import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.util.List;

public class ProductViewModel2 extends ViewModel {
    private ProductsDB productsDB;

    public ProductViewModel2(ProductsDB productsDB) {
        super();
        this.productsDB = productsDB;
    }

//    static final ViewModelInitializer<ProductViewModel2> initializer = new ViewModelInitializer<>(
//            ProductViewModel2.class,
//            creationExtras -> {
//                Application app = (Application) creationExtras.get(APPLICATION_KEY);
//                assert app != null;
//                SavedStateHandle savedStateHandle = createSavedStateHandle(creationExtras);
//
//                return new ProductViewModel2(app.getMyRepository(), savedStateHandle);
//            }
//    );

    public void insert(Product product) {
        ProductDAO productDAO = productsDB.productDAO();
        productDAO.insert(product);
    }

    public void update(Product product) {
        ProductDAO productDAO = productsDB.productDAO();
        productDAO.update(product);
    }

    public void delete(Product product) {
        ProductDAO productDAO = productsDB.productDAO();
        productDAO.delete(product);
    }

    public void deleteAllProduct() {
        productsDB.clearAllTables();
    }

    public LiveData<List<Product>> getAllProduct() {
        ProductDAO productDAO = productsDB.productDAO();
        return productDAO.selectAll();
    }
}
