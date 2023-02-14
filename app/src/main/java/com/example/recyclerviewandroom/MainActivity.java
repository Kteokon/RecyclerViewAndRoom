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
    public static final int ADD_PRODUCT_REQUEST = 1;

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
        startActivityForResult(intent, ADD_PRODUCT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            int id = products.size() + 1;

            String name = data.getStringExtra("name");
            String category = data.getStringExtra("category");
            float price = Float.parseFloat(data.getStringExtra("price"));

//            Product product = new Product(name, category, price);
//            productViewModel.insert(product);
        }
    }
}