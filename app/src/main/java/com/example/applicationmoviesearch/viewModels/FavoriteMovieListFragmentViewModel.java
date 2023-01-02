package com.example.applicationmoviesearch.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.repository.RepositoryMovies;

import java.util.List;

public class FavoriteMovieListFragmentViewModel extends ViewModel {
   public  LiveData<List<Result>> getFavoriteList (Context context){
      return RepositoryMovies.getInstance().getFavoriteMovies(context);
   }

   public void deleteMovieFav(Result result, Context context){
      RepositoryMovies.getInstance().delete(result, context);
   }
}
