package com.example.recyclerviewandroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1) // Указываем, для какой модели создаём БД. Если модель данных изменилась, надо не забыть поменять версию
public abstract class ProductsDB extends RoomDatabase {
    abstract ProductDAO productList();

    private static final String DB_NAME = "products.db";
    private static volatile ProductsDB INSTANCE = null; // volatile - данная переменная не должна кэшироваться виртуальной машиной

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
}
