package com.example.recyclerviewandroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Category.class}, version = 2)
public abstract class MyRoomDB extends RoomDatabase {
    abstract ProductDAO productDAO();
    abstract CategoryDAO categoryDAO();

    private static final String DB_NAME = "products2.db";
    private static volatile MyRoomDB INSTANCE = null;

    static MyRoomDB create(Context ctxt, boolean memoryOnly) {
        RoomDatabase.Builder<MyRoomDB> b;
        if (memoryOnly) {
            b = Room.inMemoryDatabaseBuilder(ctxt.getApplicationContext(),
                    MyRoomDB.class);
        }
        else {
            b = Room.databaseBuilder(ctxt.getApplicationContext(), MyRoomDB.class,
                    DB_NAME);
        }
        return(b.build());
    }

    synchronized static MyRoomDB get(Context ctxt) {
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
