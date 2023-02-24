package com.example.recyclerviewandroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> products;
    private LiveData<List<Category>> categories;
    private LiveData<List<CategoryWithProducts>> categoryWithProducts;
    private LiveData<List<ProductWithCategory>> productWithCategory;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ProductRepository(application);
        this.products = repository.getProducts();
        this.categories = repository.getCategories();
        this.categoryWithProducts = repository.getCategoryWithProducts();
        this.productWithCategory = repository.getProductWithCategory();
    }

    // region Product methods
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
    // endregion Product methods

    // region Category methods
    public void insert(Category category) {
        this.repository.insert(category);
    }

    public void update(Category category) {
        this.repository.update(category);
    }

    public void delete(Category category) {
        this.repository.delete(category);
    }

    public LiveData<List<Category>> getAllCategories() {
        return this.categories;
    }
    // endregion Category methods

    public LiveData<List<CategoryWithProducts>> getAllCategoryWithProducts() {
        return this.categoryWithProducts;
    }

    public LiveData<List<ProductWithCategory>> getAllProductWithCategory() {
        return this.productWithCategory;
    }

    public Category getCategoryByName(String name) {
        return repository.findCategoryByName(name);
    }
}
