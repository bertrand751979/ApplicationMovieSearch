package com.example.applicationmoviesearch.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.applicationmoviesearch.models.User;
import com.example.applicationmoviesearch.repository.RepositoryMovies;

import java.util.List;


public class LoginPageActivityViewModel extends ViewModel {
    public LiveData<List<User>> getLiveDataUser(Context context){
        return RepositoryMovies.getInstance().getUserList(context);
    }
}
