package com.example.recyclerviewandroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryRepository {
    CategoryDAO categoryDAO;
    LiveData<List<Category>> categories;

    public CategoryRepository(Application application) {
        MyRoomDB myRoomDB = MyRoomDB.get(application);
        categoryDAO = myRoomDB.categoryDAO();
        this.categories = categoryDAO.selectAll();
    }

    public void getById(int id) {
        getCategoryByIdTask task = new getCategoryByIdTask(categoryDAO);
        task.execute(id);
    }

    public static class getCategoryByIdTask extends AsyncTask<Integer, Void, Category> {
        private CategoryDAO categoryDAO;

        private getCategoryByIdTask(CategoryDAO categoryDAO) {
            this. categoryDAO =  categoryDAO;
        }

        @Override
        protected Category doInBackground(Integer... integers) {
            Category category = categoryDAO.findById(integers[0]);
            return category;
        }
    }
}
