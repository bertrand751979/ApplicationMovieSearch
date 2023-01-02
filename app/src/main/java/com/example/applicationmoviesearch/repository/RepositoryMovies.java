package com.example.applicationmoviesearch.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.applicationmoviesearch.ApplicationDatabase;
import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.models.ResultActor;
import com.example.applicationmoviesearch.models.ResultVideo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class RepositoryMovies {

    public ArrayList<Result> listMovie = new ArrayList<>();
    public ArrayList<Result> listFavoriteList = new ArrayList<>();
    public ArrayList<ResultVideo> listVideoMovies = new ArrayList<>();
    public ArrayList<Result> searchMoviesList = new ArrayList<>();
    public ArrayList<Result> recipeAlreadyFavory = new ArrayList<>();
    public ArrayList<ResultActor> actorMoviesList = new ArrayList<>();
    public ArrayList<KnownFor> listHistoryMovie = new ArrayList<>();
    public String hyperLink = "";

    private RepositoryMovies(){}
    public static RepositoryMovies INSTANCE = null;
    public static RepositoryMovies getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RepositoryMovies();
        }
        return INSTANCE;
    }

    public ArrayList<Result> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Result> listMovie) {
        this.listMovie = listMovie;
    }

    public ArrayList<Result> getListFavoriteList() {
        return listFavoriteList;
    }

    public void setListFavoriteList(ArrayList<Result> listFavoriteList) {
        this.listFavoriteList = listFavoriteList;
    }

    public ArrayList<ResultVideo> getListVideoMovies() {
        return listVideoMovies;
    }

    public void setListVideoMovies(ArrayList<ResultVideo> listVideoMovies) {
        this.listVideoMovies = listVideoMovies;
    }

    public ArrayList<Result> getSearchMoviesList() {
        return searchMoviesList;
    }

    public void setSearchMoviesList(ArrayList<Result> searchMoviesList) {
        this.searchMoviesList = searchMoviesList;
    }

    public ArrayList<Result> getRecipeAlreadyFavory() {
        return recipeAlreadyFavory;
    }

    public void setRecipeAlreadyFavory(ArrayList<Result> recipeAlreadyFavory) {
        this.recipeAlreadyFavory = recipeAlreadyFavory;
    }

    public ArrayList<ResultActor> getActorMoviesList() {
        return actorMoviesList;
    }

    public void setActorMoviesList(ArrayList<ResultActor> actorMoviesList) {
        this.actorMoviesList = actorMoviesList;
    }

    public ArrayList<KnownFor> getListHistoryMovie() {
        return listHistoryMovie;
    }

    public void setListHistoryMovie(ArrayList<KnownFor> listHistoryMovie) {
        this.listHistoryMovie = listHistoryMovie;
    }

    public LiveData<List<Result>> getFavoriteMovies (Context context){
        return ApplicationDatabase.getInstance(context).getResultDao().getFavMovies();
    }


    public void history(KnownFor knowFor){
        listHistoryMovie.add(knowFor);
    }

    public void delete(Result result, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabase.getInstance(context).getResultDao().deleteFavory(result);
            }
        });
    }

    public boolean choiceYear(String textSearch) {
        boolean res = false;
        for (Result result : RepositoryMovies.getInstance().listMovie) {
            if (result.getRelease_date().toLowerCase().contains(textSearch)) {
                res = true;
                searchMoviesList.add(result);
            }
        }
        return res;
    }

    public void addToListFavorite(Result result, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabase.getInstance(context).getResultDao().insertFavori(result);
            }
        });
    }




    public String toCreateVideoHyperlinkById(String variable){
        int pos =0;
        for(ResultVideo resultVideo1 :listVideoMovies) {
            if(resultVideo1.getSite().equalsIgnoreCase(variable)){
                Log.d("id",resultVideo1.getId());
                hyperLink= "https://www.youtube.com/watch?v="+resultVideo1.getKey();
                Log.d("pos", String.valueOf(pos));
            }
        }
        return hyperLink;
    }
    public boolean isFavorite(Result movieSelected){
        boolean result=  false;
        for(Result resultFav: listFavoriteList){
            if(resultFav.getRelease_date().equals(movieSelected.getRelease_date())){
                result = true;
            }
        }
        return result;
    }
}
