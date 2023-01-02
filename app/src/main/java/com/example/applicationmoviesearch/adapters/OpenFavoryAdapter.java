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
import com.example.applicationmoviesearch.viewHolders.OpenFavoryViewHolder;

import java.util.ArrayList;

public class OpenFavoryAdapter extends RecyclerView.Adapter<OpenFavoryViewHolder> {
    private ArrayList<Result> listOpenFavAdapter = new ArrayList<>();
    private ActionToGoToDetail actionToGoToDetail;
    private ActionDeleteMovieFavory actionDeleteMovieFavory;

    public OpenFavoryAdapter(ActionToGoToDetail actionToGoToDetail, ActionDeleteMovieFavory actionDeleteMovieFavory) {
        this.actionToGoToDetail = actionToGoToDetail;
        this.actionDeleteMovieFavory = actionDeleteMovieFavory;
    }

    public void setListOpenFavAdapter(ArrayList<Result> listOpenFavAdapter) {
        this.listOpenFavAdapter = listOpenFavAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OpenFavoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_open_favorite_movie_list,parent,false);
        return new OpenFavoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenFavoryViewHolder holder, int position) {
        holder.bind(listOpenFavAdapter.get(position),actionDeleteMovieFavory,actionToGoToDetail);
    }

    @Override
    public int getItemCount() {
        return listOpenFavAdapter.size();
    }
}
