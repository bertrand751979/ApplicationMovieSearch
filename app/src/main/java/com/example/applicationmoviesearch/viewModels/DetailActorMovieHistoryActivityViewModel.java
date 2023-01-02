package com.example.applicationmoviesearch.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.models.ResultActor;
import com.example.applicationmoviesearch.repository.RepositoryMovies;

import java.util.ArrayList;
import java.util.List;

public class DetailActorMovieHistoryActivityViewModel extends ViewModel {
    private MutableLiveData<List<KnownFor>> listActorMovieMutable = new MutableLiveData<>();
    public LiveData<List<KnownFor>> liveDataListHistory = listActorMovieMutable;

    public void toPost(ArrayList<KnownFor> list){
        listActorMovieMutable.postValue(list);
    }


}
