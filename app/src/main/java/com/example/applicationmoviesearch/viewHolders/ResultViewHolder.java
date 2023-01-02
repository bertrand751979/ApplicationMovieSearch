package com.example.applicationmoviesearch.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationmoviesearch.ActionToGoToDetail;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.Result;

public class ResultViewHolder extends RecyclerView.ViewHolder {
    private ImageView vhMoviePoster;
    private TextView vhMovieTitle;
    private TextView vhMovieDateRelease;
    private TextView vhMovieOverview;
    private LinearLayout vhMovieLinearLayout;

    public ResultViewHolder(@NonNull View view) {
        super(view);
        vhMoviePoster = view.findViewById(R.id.raw_movie_poster_movie);
        vhMovieTitle = view.findViewById(R.id.raw_movie_title);
        vhMovieDateRelease = view.findViewById(R.id.raw_movie_release_date);
        vhMovieOverview = view.findViewById(R.id.raw_movie_overview);
        vhMovieLinearLayout = view.findViewById(R.id.raw_movie_linear_layout);
    }

    public ImageView getVhMoviePoster() {
        return vhMoviePoster;
    }

    public void setVhMoviePoster(ImageView vhMoviePoster) {
        this.vhMoviePoster = vhMoviePoster;
    }

    public TextView getVhMovieTitle() {
        return vhMovieTitle;
    }

    public void setVhMovieTitle(TextView vhMovieTitle) {
        this.vhMovieTitle = vhMovieTitle;
    }

    public TextView getVhMovieDateRelease() {
        return vhMovieDateRelease;
    }

    public void setVhMovieDateRelease(TextView vhMovieDateRelease) {
        this.vhMovieDateRelease = vhMovieDateRelease;
    }

    public TextView getVhMovieOverview() {
        return vhMovieOverview;
    }

    public void setVhMovieOverview(TextView vhMovieOverview) {
        this.vhMovieOverview = vhMovieOverview;
    }

    public LinearLayout getVhMovieLinearLayout() {
        return vhMovieLinearLayout;
    }

    public void setVhMovieLinearLayout(LinearLayout vhMovieLinearLayout) {
        this.vhMovieLinearLayout = vhMovieLinearLayout;
    }

    public void bind (Result result, ActionToGoToDetail actionToGoToDetail){
        String downloadPhoto = "https://image.tmdb.org/t/p/w500"+result.getPoster_path();
        if(downloadPhoto != null){
        Glide.with(vhMoviePoster.getContext())
                .load(downloadPhoto)
                .into(vhMoviePoster);}
        else{
            vhMoviePoster.setImageResource(R.drawable.ic_delete);
        }
        vhMovieTitle.setText(result.getOriginal_title());
        vhMovieDateRelease.setText(result.getRelease_date());
        vhMovieOverview.setText(result.getOverview());
        vhMovieLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionToGoToDetail.goToDetail(result);
            }
        });
    }
}
