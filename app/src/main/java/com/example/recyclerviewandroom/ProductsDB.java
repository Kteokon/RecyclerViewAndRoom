package com.example.recyclerviewandroom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductsDB extends RoomDatabase {
    abstract ProductDAO productDAO();

    private static final String DB_NAME = "products.db";
    private static volatile ProductsDB INSTANCE = null;

    static ProductsDB create(Context ctxt, boolean memoryOnly) {
        RoomDatabase.Builder<ProductsDB> b;
        if (memoryOnly) {
            b = Room.inMemoryDatabaseBuilder(ctxt.getApplicationContext(),
                    ProductsDB.class);
        }
        else {
            b = Room.databaseBuilder(ctxt.getApplicationContext(), ProductsDB.class,
                    DB_NAME);
        }
        return(b.build());
    }

    synchronized static ProductsDB get(Context ctxt) {
        if (INSTANCE == null) {
            INSTANCE = create(ctxt, false);
        }
        return(INSTANCE);
    }

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//        }
//    };
//
//    private static class PopulateDBTask extends AsyncTask<Void, Void, Void> {
//        private ProductDAO productDAO;
//        private PopulateDBTask(ProductsDB productsDB) {
//            this.productDAO = productsDB.productDAO();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            productDAO.insert(new Product());
//            return null;
//        }
//    }
}
