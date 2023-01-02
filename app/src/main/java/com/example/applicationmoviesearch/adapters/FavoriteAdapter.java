package com.example.applicationmoviesearch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmoviesearch.ActionDeleteMovieFavory;
import com.example.applicationmoviesearch.ActionToGoToDetail;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.viewHolders.FavoriteViewHolder;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {
    private ArrayList<Result> listFavoriteAdapter = new ArrayList<>();
    private ActionToGoToDetail actionToGoToDetail;
    private ActionDeleteMovieFavory actionDeleteMovieFavory;

    public FavoriteAdapter(ActionToGoToDetail actionToGoToDetail, ActionDeleteMovieFavory actionDeleteMovieFavory) {
        this.actionToGoToDetail = actionToGoToDetail;
        this.actionDeleteMovieFavory = actionDeleteMovieFavory;
    }

    public void setListFavoriteAdapter(ArrayList<Result> listFavoriteAdapter) {
        this.listFavoriteAdapter = listFavoriteAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_favorite_movie_list,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(listFavoriteAdapter.get(position),actionToGoToDetail,actionDeleteMovieFavory );
    }

    @Override
    public int getItemCount() {
        return listFavoriteAdapter.size();
    }
}
