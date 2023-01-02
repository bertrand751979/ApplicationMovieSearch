package com.example.applicationmoviesearch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmoviesearch.ActionGoToDetailActor;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.ResultActor;
import com.example.applicationmoviesearch.viewHolders.ActorViewHolder;

import java.util.ArrayList;

public class ActorAdapter extends RecyclerView.Adapter<ActorViewHolder> {
    private ArrayList<ResultActor> listAdapterActor = new ArrayList<>();
    private ActionGoToDetailActor actionGoToDetailActor;

    public ActorAdapter(ActionGoToDetailActor actionGoToDetailActor) {
        this.actionGoToDetailActor = actionGoToDetailActor;
    }

    public void setListAdapterActor(ArrayList<ResultActor> listAdapterActor) {
        this.listAdapterActor = listAdapterActor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_actor_movie_list,parent,false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        holder.bind(listAdapterActor.get(position), actionGoToDetailActor);
    }

    @Override
    public int getItemCount() {
        return listAdapterActor.size();
    }
}
