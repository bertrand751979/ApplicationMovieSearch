package com.example.applicationmoviesearch.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.applicationmoviesearch.ActionGoToDetailActor;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.models.ResultActor;

public class ActorViewHolder extends RecyclerView.ViewHolder {
    private TextView vhActorMovieName;
    private ImageView vhActorMovieProfilPath;
    private ImageView vhBtnDisplayMovies;

    public ActorViewHolder(@NonNull View view) {
        super(view);
        vhActorMovieName = view.findViewById(R.id.raw_actor_movie_name);
        vhActorMovieProfilPath = view.findViewById(R.id.raw_actor_movie_profil_path);
        vhBtnDisplayMovies = view.findViewById(R.id.raw_actor_movie_detail);
    }

    public TextView getVhActorMovieName() {
        return vhActorMovieName;
    }

    public void setVhActorMovieName(TextView vhActorMovieName) {
        this.vhActorMovieName = vhActorMovieName;
    }

    public ImageView getVhActorMovieProfilPath() {
        return vhActorMovieProfilPath;
    }

    public void setVhActorMovieProfilPath(ImageView vhActorMovieProfilPath) {
        this.vhActorMovieProfilPath = vhActorMovieProfilPath;
    }

    public ImageView getVhBtnDisplayMovies() {
        return vhBtnDisplayMovies;
    }

    public void setVhBtnDisplayMovies(ImageView vhBtnDisplayMovies) {
        this.vhBtnDisplayMovies = vhBtnDisplayMovies;
    }

    public void bind (ResultActor resultActor, ActionGoToDetailActor actionGoToDetailActor){
        String profilPath ="https://image.tmdb.org/t/p/w500"+resultActor.getProfile_path();
        if(profilPath == null){
            vhActorMovieProfilPath.setImageResource(R.drawable.ic_full_star);
        }else{
        vhActorMovieName.setText(resultActor.getName());
        Glide.with(vhActorMovieProfilPath.getContext())
                        .load(profilPath)
                            .centerCrop()
                                .apply(RequestOptions.circleCropTransform())
                                    .into(vhActorMovieProfilPath);}
        vhBtnDisplayMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToDetailActor.goToHistoryActorMovies(resultActor);
            }
        });
    }
}
