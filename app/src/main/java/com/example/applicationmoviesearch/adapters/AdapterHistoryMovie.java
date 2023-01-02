package com.example.applicationmoviesearch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.models.ResultActor;
import com.example.applicationmoviesearch.viewHolders.HistoryMovieViewHolder;

import java.util.ArrayList;

public class AdapterHistoryMovie extends RecyclerView.Adapter<HistoryMovieViewHolder> {
    private ArrayList<KnownFor> listAdapterHistory = new ArrayList<>();

   /* public AdapterHistoryMovie(ArrayList<KnownFor> listAdapterHistory) {
        this.listAdapterHistory = listAdapterHistory;
    }*/

    public AdapterHistoryMovie() {
    }

    public void setListAdapterHistory(ArrayList<KnownFor> listAdapterHistory) {
        this.listAdapterHistory = listAdapterHistory;
    }

    @NonNull
    @Override
    public HistoryMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_history_title,parent,false);
        return new HistoryMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryMovieViewHolder holder, int position) {
        holder.bind(listAdapterHistory.get(position));
    }

    @Override
    public int getItemCount() {
        return listAdapterHistory.size();
    }
}
