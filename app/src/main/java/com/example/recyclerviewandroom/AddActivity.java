package com.example.recyclerviewandroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText nameET, categoryET, priceET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameET = findViewById(R.id.name);
        categoryET = findViewById(R.id.category);
        priceET = findViewById(R.id.price);
    }

    public void addProduct(View v) {
        String name = nameET.getText().toString();
        String category = categoryET.getText().toString();
        String price = priceET.getText().toString();

        Intent data = new Intent();
        data.putExtra("name", name);
        data.putExtra("category", category);
        data.putExtra("price", price);

        setResult(RESULT_OK, data);
        finish();
    }
}