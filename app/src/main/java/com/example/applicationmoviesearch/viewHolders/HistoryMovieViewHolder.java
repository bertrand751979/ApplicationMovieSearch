package com.example.applicationmoviesearch.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.repository.RepositoryMovies;

public class HistoryMovieViewHolder extends RecyclerView.ViewHolder {
    private TextView vhHistoryActorTitle;
    private ImageView vhHistoryActorPoster;

    public HistoryMovieViewHolder(@NonNull View view) {
        super(view);
        vhHistoryActorTitle = view.findViewById(R.id.actor_history_name_movie);
        vhHistoryActorPoster = view.findViewById(R.id.actor_history_poster_movie);

    }

    public TextView getVhHistoryActorTitle() {
        return vhHistoryActorTitle;
    }

    public void setVhHistoryActorTitle(TextView vhHistoryActorTitle) {
        this.vhHistoryActorTitle = vhHistoryActorTitle;
    }

    public ImageView getVhHistoryActorPoster() {
        return vhHistoryActorPoster;
    }

    public void setVhHistoryActorPoster(ImageView vhHistoryActorPoster) {
        this.vhHistoryActorPoster = vhHistoryActorPoster;
    }

    public void bind(KnownFor knownFor){
        vhHistoryActorTitle.setText(knownFor.getOriginal_title());
        String profilPath ="https://image.tmdb.org/t/p/w500"+knownFor.getPoster_path();
        if(profilPath == null){
            vhHistoryActorPoster.setImageResource(R.drawable.ic_full_star);
        }else{
            Glide.with(vhHistoryActorPoster.getContext())
                    .load(profilPath)
                    .centerCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .into(vhHistoryActorPoster);}

    }

}
