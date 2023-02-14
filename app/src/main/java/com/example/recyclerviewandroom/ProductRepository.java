package com.example.recyclerviewandroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    ProductDAO productDAO;
    LiveData<List<Product>> products;

    public ProductRepository(Application application) {
        MyRoomDB myRoomDB = MyRoomDB.get(application);
        this.productDAO = myRoomDB.productDAO();
        this.products = productDAO.selectAll();
    }

    public void insert(Product product) {
        new InsertProductTask(productDAO).execute(product);
    }

    public void update(Product product) {
        new InsertProductTask(productDAO).execute(product);
    }

    public void delete(Product product) {
        new DeleteProductTask(productDAO).execute(product);
    }

    public void deleteAllProducts(Application application) {
        new DeleteAllProductsTask(productDAO).execute(application);
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public static class InsertProductTask extends AsyncTask<Product, Void, Void> {
        private ProductDAO productDAO;

        private InsertProductTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.insert(products[0]);
            return null;
        }
    }

    public static class UpdateProductTask extends AsyncTask<Product, Void, Void> {
        private ProductDAO productDAO;

        private UpdateProductTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.update(products[0]);
            return null;
        }
    }

    public static class DeleteProductTask extends AsyncTask<Product, Void, Void> {
        private ProductDAO productDAO;

        private DeleteProductTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.delete(products[0]);
            return null;
        }
    }

    public static class DeleteAllProductsTask extends AsyncTask<Application, Void, Void> {
        private ProductDAO productDAO;

        private DeleteAllProductsTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Application... applications) {
            MyRoomDB myRoomDB = MyRoomDB.get(applications[0]);
            myRoomDB.clearAllTables();
            return null;
        }
    }
}
