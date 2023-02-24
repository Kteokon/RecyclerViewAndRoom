package com.example.recyclerviewandroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductRepository {
    ProductDAO productDAO;
    LiveData<List<Product>> products;
    LiveData<List<Category>> categories;
    LiveData<List<CategoryWithProducts>> categoryWithProducts;
    LiveData<List<ProductWithCategory>> productWithCategory;

    public ProductRepository(Application application) {
        MyRoomDB myRoomDB = MyRoomDB.get(application);
        this.productDAO = myRoomDB.productDAO();
        this.products = productDAO.selectAllProducts();
        this.categories = productDAO.selectAllCategories();
        this.categoryWithProducts = productDAO.selectAllCategoryWithProducts();
        this.productWithCategory = productDAO.selectAllProductWithCategory();
    }

    // region Product methods
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
    // endregion Product methods

    // region Category methods
    public void insert(Category category) {
        new InsertCategoryTask(productDAO).execute(category);
    }

    public void update(Category category) {
        new InsertCategoryTask(productDAO).execute(category);
    }

    public void delete(Category category) {
        new DeleteCategoryTask(productDAO).execute(category);
    }

    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public Category findCategoryByName(String name) {
        FindCategoryByNameTask task = new FindCategoryByNameTask(productDAO);
        Category category = null;
        try {
            category = task.execute(name).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return category;
    }

    public static class InsertCategoryTask extends AsyncTask<Category, Void, Void> {
        private ProductDAO productDAO;

        private InsertCategoryTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            productDAO.insert(categories[0]);
            return null;
        }
    }

    public static class UpdateCategoryTask extends AsyncTask<Category, Void, Void> {
        private ProductDAO productDAO;

        private UpdateCategoryTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            productDAO.update(categories[0]);
            return null;
        }
    }

    public static class DeleteCategoryTask extends AsyncTask<Category, Void, Void> {
        private ProductDAO productDAO;

        private DeleteCategoryTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            productDAO.delete(categories[0]);
            return null;
        }
    }

    public static class FindCategoryByNameTask extends AsyncTask<String, Void, Category> {
        private ProductDAO productDAO;

        private FindCategoryByNameTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Category doInBackground(String... strings) {
            Category category = productDAO.findCategoryByName(strings[0]);
            return category;
        }
    }
    // endregion Category methods

    public LiveData<List<CategoryWithProducts>> getCategoryWithProducts() {
        return categoryWithProducts;
    }

    public LiveData<List<ProductWithCategory>> getProductWithCategory() {
        return productWithCategory;
    }


}
