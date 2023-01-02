package com.example.applicationmoviesearch;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.models.Result;

import java.util.List;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM Result ")
    LiveData<List<Result>> getFavMovies();

    @Delete
    void deleteFavory(Result result);


    @Insert
    void insertFavori(Result result);





}
