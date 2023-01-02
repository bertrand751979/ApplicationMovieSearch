package com.example.applicationmoviesearch.viewModels;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.repository.RepositoryMovies;

import java.util.List;

public class DetailActivityViewModel extends ViewModel {
    public void addToFavorite(Result result, Context context){
        RepositoryMovies.getInstance().addToListFavorite(result, context);
    }

    public LiveData<List<Result>> getFavoriteList(Context context){
        return RepositoryMovies.getInstance().getFavoriteMovies(context);
    }
}
