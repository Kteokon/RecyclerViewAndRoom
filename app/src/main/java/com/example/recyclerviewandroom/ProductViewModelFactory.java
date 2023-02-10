package com.example.recyclerviewandroom;

import androidx.lifecycle.ViewModelProvider;

public class ProductViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ProductDAO productDAO;

    public void ProductViewModel(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
