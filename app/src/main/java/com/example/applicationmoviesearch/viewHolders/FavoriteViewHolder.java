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

public class FavoriteViewHolder extends RecyclerView.ViewHolder {
    private ImageView vhFavoriteMoviePoster;
    private TextView vhFavoriteMovieTitle;
    private TextView vhFavoriteMovieDateRelease;
    private TextView vhFavoriteMovieOverview;
    private LinearLayout vhFavoriteMovieLinearLayout;
    private ImageView vhFavoriteBtnDelete;

    public FavoriteViewHolder(@NonNull View view) {
        super(view);
        vhFavoriteMoviePoster = view.findViewById(R.id.raw_favorite_movie_poster_movie);
        vhFavoriteMovieTitle = view.findViewById(R.id.raw_favorite_movie_title);
        vhFavoriteMovieDateRelease = view.findViewById(R.id.raw_favorite_movie_release_date);
        vhFavoriteMovieOverview = view.findViewById(R.id.raw_favorite_movie_overview);
        vhFavoriteMovieLinearLayout = view.findViewById(R.id.raw_favorite_movie_linear_layout);
        vhFavoriteBtnDelete = view.findViewById(R.id.raw_favorite_delete);
    }

    public ImageView getVhFavoriteMoviePoster() {
        return vhFavoriteMoviePoster;
    }

    public void setVhFavoriteMoviePoster(ImageView vhFavoriteMoviePoster) {
        this.vhFavoriteMoviePoster = vhFavoriteMoviePoster;
    }

    public TextView getVhFavoriteMovieTitle() {
        return vhFavoriteMovieTitle;
    }

    public void setVhFavoriteMovieTitle(TextView vhFavoriteMovieTitle) {
        this.vhFavoriteMovieTitle = vhFavoriteMovieTitle;
    }
    public TextView getVhFavoriteMovieDateRelease() {
        return vhFavoriteMovieDateRelease;
    }

    public void setVhFavoriteMovieDateRelease(TextView vhFavoriteMovieDateRelease) {
        this.vhFavoriteMovieDateRelease = vhFavoriteMovieDateRelease;
    }
    public TextView getVhFavoriteMovieOverview() {
        return vhFavoriteMovieOverview;
    }

    public void setVhFavoriteMovieOverview(TextView vhFavoriteMovieOverview) {
        this.vhFavoriteMovieOverview = vhFavoriteMovieOverview;
    }
    public LinearLayout getVhFavoriteMovieLinearLayout() {
        return vhFavoriteMovieLinearLayout;
    }

    public void setVhFavoriteMovieLinearLayout(LinearLayout vhFavoriteMovieLinearLayout) {
        this.vhFavoriteMovieLinearLayout = vhFavoriteMovieLinearLayout;
    }

    public ImageView getVhFavoriteBtnDelete() {
        return vhFavoriteBtnDelete;
    }

    public void setVhFavoriteBtnDelete(ImageView vhFavoriteBtnDelete) {
        this.vhFavoriteBtnDelete = vhFavoriteBtnDelete;
    }
    public void bind(Result result, ActionToGoToDetail actionToGoToDetail,ActionDeleteMovieFavory actionDeleteMovieFavory){
        String posterMovieFav ="https://image.tmdb.org/t/p/w500"+result.getPoster_path();
        if(posterMovieFav != null){
        Glide.with(vhFavoriteMoviePoster.getContext())
                        .load(posterMovieFav)
                                .into(vhFavoriteMoviePoster);
        }else{vhFavoriteMoviePoster.setImageResource(R.drawable.ic_delete);}
        vhFavoriteMovieTitle.setText(result.getOriginal_title());
        vhFavoriteMovieDateRelease.setText(result.getRelease_date());
        vhFavoriteMovieOverview.setText(result.getOverview());
        vhFavoriteMovieLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionToGoToDetail.goToDetail(result);
            }
        });
        vhFavoriteBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionDeleteMovieFavory.deleteFavory(result);
            }
        });
    }
}
