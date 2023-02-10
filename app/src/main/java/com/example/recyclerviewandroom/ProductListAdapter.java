package com.example.recyclerviewandroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.CustomViewHolder>{
    private List<Product> products = new ArrayList<>();
    LayoutInflater inflater;
    ItemClickListener listener;

    public ProductListAdapter(Context context, ItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Product product = products.get(position);
        holder.idTV.setText(Integer.toString(product.getId()));
        holder.nameTV.setText(product.getName());
        holder.categoryTV.setText(product.getCategory());
        holder.priceTV.setText(Float.toString(product.getPrice()));
        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged(); // не использовать в RecycleView
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView idTV, nameTV, categoryTV, priceTV;
        ConstraintLayout cl;

        public CustomViewHolder(View view) {
            super(view);

            idTV = view.findViewById(R.id.id);
            nameTV = view.findViewById(R.id.name);
            categoryTV = view.findViewById(R.id.category);
            priceTV = view.findViewById(R.id.price);
            cl = view.findViewById(R.id.recycle_view_layout);
        }
    }
}
