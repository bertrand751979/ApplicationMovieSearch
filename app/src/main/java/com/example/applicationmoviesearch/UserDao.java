package com.example.applicationmoviesearch;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.applicationmoviesearch.models.User;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM User ")
    LiveData<List<User>> getUsers();

    @Insert
    void add(User user);


}
