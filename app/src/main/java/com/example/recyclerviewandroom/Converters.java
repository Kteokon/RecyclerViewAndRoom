package com.example.recyclerviewandroom;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static Category nameToCategory(String name) {
        return name == null ? null : new Category(name);
    }

    @TypeConverter
    public static String categoryToName(Category category) {
        return category == null ? null : category.getName();
    }
}
