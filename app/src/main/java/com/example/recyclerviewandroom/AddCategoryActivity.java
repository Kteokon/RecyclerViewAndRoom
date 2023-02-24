package com.example.recyclerviewandroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity {
    EditText nameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);

        nameET = findViewById(R.id.name);
    }

    public void saveCategory(View v) {
        String name = nameET.getText().toString();

        Intent data = new Intent();
        data.putExtra("name", name);

        setResult(RESULT_OK, data);
        finish();
    }
}