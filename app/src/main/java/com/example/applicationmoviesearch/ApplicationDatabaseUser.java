package com.example.applicationmoviesearch;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.applicationmoviesearch.models.User;


@Database(entities = {User.class},version = 10)
public abstract class ApplicationDatabaseUser extends RoomDatabase {
    private static ApplicationDatabaseUser INSTANCE;
    public abstract UserDao getUserDao();
    public static synchronized ApplicationDatabaseUser getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabaseUser.class, "user_app").build();
        }
        return INSTANCE;
    }
}
