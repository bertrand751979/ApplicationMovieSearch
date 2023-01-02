package com.example.applicationmoviesearch.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applicationmoviesearch.models.ResultActor;
import com.example.applicationmoviesearch.repository.RepositoryMovies;

import java.util.List;

public class ActorMoviesListFragmentViewModel extends ViewModel {
    private MutableLiveData<List<ResultActor>> listActorMutable = new MutableLiveData<>();
    public LiveData<List<ResultActor>> liveDataActor = listActorMutable;

    public void toPostListActor(){
        listActorMutable.postValue(RepositoryMovies.getInstance().actorMoviesList);
    }
}
