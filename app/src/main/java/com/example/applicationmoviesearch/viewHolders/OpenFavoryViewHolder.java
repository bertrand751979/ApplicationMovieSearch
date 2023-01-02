package com.example.applicationmoviesearch.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationmoviesearch.ActionDeleteMovieFavory;
import com.example.applicationmoviesearch.ActionToGoToDetail;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.Result;

public class OpenFavoryViewHolder extends RecyclerView.ViewHolder {
    private ImageView vhOpenFavoriteMoviePoster;
    private TextView vhOpenFavoriteMovieTitle;
    private TextView vhOpenFavoriteMovieDateRelease;
    private TextView vhOpenFavoriteMovieOverview;
    private LinearLayout vhOpenFavoriteMovieLinearLayout;
    private ImageView vhOpenFavoriteBtnDelete;

    public OpenFavoryViewHolder(@NonNull View view) {
        super(view);
        vhOpenFavoriteMoviePoster = view.findViewById(R.id.raw_open_favorite_movie_poster_movie);
        vhOpenFavoriteMovieTitle = view.findViewById(R.id.raw_open_favorite_movie_title);
        vhOpenFavoriteMovieDateRelease = view.findViewById(R.id.raw_open_favorite_movie_release_date);
        vhOpenFavoriteMovieOverview = view.findViewById(R.id.raw_open_favorite_movie_overview);
        vhOpenFavoriteMovieLinearLayout = view.findViewById(R.id.raw_open_favorite_movie_linear_layout);
        vhOpenFavoriteBtnDelete = view.findViewById(R.id.raw_open_favorite_delete);
    }

    public ImageView getVhOpenFavoriteMoviePoster() {
        return vhOpenFavoriteMoviePoster;
    }

    public void setVhOpenFavoriteMoviePoster(ImageView vhOpenFavoriteMoviePoster) {
        this.vhOpenFavoriteMoviePoster = vhOpenFavoriteMoviePoster;
    }

    public TextView getVhOpenFavoriteMovieTitle() {
        return vhOpenFavoriteMovieTitle;
    }

    public void setVhOpenFavoriteMovieTitle(TextView vhOpenFavoriteMovieTitle) {
        this.vhOpenFavoriteMovieTitle = vhOpenFavoriteMovieTitle;
    }

    public TextView getVhOpenFavoriteMovieDateRelease() {
        return vhOpenFavoriteMovieDateRelease;
    }

    public void setVhOpenFavoriteMovieDateRelease(TextView vhOpenFavoriteMovieDateRelease) {
        this.vhOpenFavoriteMovieDateRelease = vhOpenFavoriteMovieDateRelease;
    }

    public TextView getVhOpenFavoriteMovieOverview() {
        return vhOpenFavoriteMovieOverview;
    }

    public void setVhOpenFavoriteMovieOverview(TextView vhOpenFavoriteMovieOverview) {
        this.vhOpenFavoriteMovieOverview = vhOpenFavoriteMovieOverview;
    }

    public LinearLayout getVhOpenFavoriteMovieLinearLayout() {
        return vhOpenFavoriteMovieLinearLayout;
    }

    public void setVhOpenFavoriteMovieLinearLayout(LinearLayout vhOpenFavoriteMovieLinearLayout) {
        this.vhOpenFavoriteMovieLinearLayout = vhOpenFavoriteMovieLinearLayout;
    }

    public ImageView getVhOpenFavoriteBtnDelete() {
        return vhOpenFavoriteBtnDelete;
    }

    public void setVhOpenFavoriteBtnDelete(ImageView vhOpenFavoriteBtnDelete) {
        this.vhOpenFavoriteBtnDelete = vhOpenFavoriteBtnDelete;
    }

    public void bind (Result result, ActionDeleteMovieFavory actionDeleteMovieFavory, ActionToGoToDetail actionToGoToDetail){
        String posterOpenMovieFav ="https://image.tmdb.org/t/p/w500"+result.getPoster_path();
        if(posterOpenMovieFav != null){
            Glide.with(vhOpenFavoriteMoviePoster.getContext())
                    .load(posterOpenMovieFav)
                    .into(vhOpenFavoriteMoviePoster);
        }else{vhOpenFavoriteMoviePoster.setImageResource(R.drawable.ic_delete);}


        //vhOpenFavoriteMovieTitle.setText(result.getOriginal_title());
        //vhOpenFavoriteMovieDateRelease.setText(result.getRelease_date());
        //vhOpenFavoriteMovieOverview.setText(result.getOverview());
        vhOpenFavoriteMovieLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionToGoToDetail.goToDetail(result);
            }
        });
        vhOpenFavoriteBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionDeleteMovieFavory.deleteFavory(result);
            }
        });





    }
}
