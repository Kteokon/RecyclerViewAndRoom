package com.example.recyclerviewandroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener{
    DBHelperWithLoader dbHelper;
    ProductsDB productsdb;

    private ProductViewModel productViewModel;
    private RecyclerView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelperWithLoader(this);
        productsdb = ProductsDB.create(this, false);

        productList = findViewById(R.id.productList);
        productList.setLayoutManager(new LinearLayoutManager(this));

        ProductListAdapter adapter = new ProductListAdapter(this, this);
        productList.setAdapter(adapter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProduct().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
                // update RecycleView
                Log.d("mytag", "Records in adapter: " + adapter.getItemCount());
            }
        });
    }

    @Override
    public void onItemClick(Product item) {
        Log.d("mytag", item.getName());
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}