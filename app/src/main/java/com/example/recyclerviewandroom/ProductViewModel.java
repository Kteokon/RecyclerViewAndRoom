package com.example.recyclerviewandroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ProductRepository(application);
        this.products = repository.getProducts();
    }

    public void insert(Product product) {
        this.repository.insert(product);
    }

    public void update(Product product) {
        this.repository.update(product);
    }

    public void delete(Product product) {
        this.repository.delete(product);
    }

    public void deleteAllProduct(@NonNull Application application) {
        this.repository.deleteAllProducts(application);
    }

    public LiveData<List<Product>> getAllProduct() {
        return this.products;
    }
}
