package com.example.applicationmoviesearch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applicationmoviesearch.ActionToGoToDetail;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.viewHolders.ResultViewHolder;
import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {
    private ArrayList<Result> listResultAdapter = new ArrayList<>();
    private ActionToGoToDetail actionToGoToDetail;

    public ResultAdapter(ActionToGoToDetail actionToGoToDetail) {
        this.actionToGoToDetail = actionToGoToDetail;
    }

    public void setListResultAdapter(ArrayList<Result> listResultAdapter) {
        this.listResultAdapter = listResultAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_movie_list,parent,false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.bind(listResultAdapter.get(position),actionToGoToDetail );
    }

    @Override
    public int getItemCount() {
        return listResultAdapter.size();
    }
}
