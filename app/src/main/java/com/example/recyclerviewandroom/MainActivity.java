package com.example.recyclerviewandroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    DBHelperWithLoader dbHelper;
    MyRoomDB myRoomDB;
    private List<Product> products;
    private List<ProductWithCategory> productWithCategories;
    private List<Category> categories;
    public static final int ADD_PRODUCT_REQUEST = 1;
    public static final int ADD_CATEGORY_REQUEST = 2;

    private ProductViewModel productViewModel;
    private RecyclerView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelperWithLoader(this);
        myRoomDB = MyRoomDB.create(this, false);

        productList = findViewById(R.id.productList);
        productList.setLayoutManager(new LinearLayoutManager(this));

        ProductListAdapter adapter = new ProductListAdapter(this, this);
        productList.setAdapter(adapter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProduct().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> _products) {
                products = _products;
                adapter.setProducts(products);
                // update RecycleView
            }
        });

        productViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> _categories) {
                categories = _categories;
                Log.d("mytag", "Categories size: " + categories.size());
            }
        });

        productViewModel.getAllProductWithCategory().observe(this, new Observer<List<ProductWithCategory>>() {
            @Override
            public void onChanged(List<ProductWithCategory> _productWithCategories) {
                productWithCategories = _productWithCategories;
                Log.d("mytag", "Product with categories size: " + productWithCategories.size());
                Log.d("mytag", "4 product's category id: " + productWithCategories.get(3).category.getId());
            }
        });
    }

    @Override
    public void onItemClick(Product item) {
        Log.d("mytag", item.getName());
    }

    public void addProduct(View v) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivityForResult(intent, ADD_PRODUCT_REQUEST);
    }

    public void addCategory(View v) {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivityForResult(intent, ADD_CATEGORY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            int id = products.size() + 1;

            String name = data.getStringExtra("name");
            String categoryName = data.getStringExtra("category");
            float price = Float.parseFloat(data.getStringExtra("price"));

            Category category = productViewModel.getCategoryByName(categoryName);
            if (category == null) {
                category = new Category(categoryName);
                category.setId(categories.size() + 1);
                productViewModel.insert(category);
            }
            Product product = new Product(name, category.getId(), price);
            Log.d("mytag", category.getName());

            productViewModel.insert(product);
        }

        if (requestCode == ADD_CATEGORY_REQUEST && resultCode == RESULT_OK) {
            //int id = categories.size() + 1;

            String name = data.getStringExtra("name");

//            Product product = new Product(name, category, price);
//            productViewModel.insert(product);
        }
    }
}