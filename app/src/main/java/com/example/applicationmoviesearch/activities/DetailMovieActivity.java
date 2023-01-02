package com.example.applicationmoviesearch.activities;

import static com.example.applicationmoviesearch.activities.MainActivity.MOVIE_EXTRA;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.applicationmoviesearch.MovieRetrofitApi;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.models.ResultVideo;
import com.example.applicationmoviesearch.models.RootVideo;
import com.example.applicationmoviesearch.repository.RepositoryMovies;
import com.example.applicationmoviesearch.viewModels.DetailActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {

    private ImageView descPoster;
    private TextView descDetail;
    private ImageView descFavory;
    private TextView descReleaseDate;
    private TextView descVoteCount;
    private TextView descPopularity;
    private TextView descTitle;
    private Result result;
    private ResultVideo resultVideo;
    private Button descVids;
    private DetailActivityViewModel detailActivityViewModel;
    public  String videoHyperlinkCreation ="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detailActivityViewModel = new ViewModelProvider(this).get(DetailActivityViewModel.class);
        result = (Result) getIntent().getSerializableExtra(MOVIE_EXTRA);
        setTitle("Description");
        descPoster = findViewById(R.id.desc_poster);
        descDetail = findViewById(R.id.desc_movie_detail);
        descFavory = findViewById(R.id.desc_img_fav);
        descReleaseDate = findViewById(R.id.desc_release_date);
        descVoteCount = findViewById(R.id.desc_movie_vote_count);
        descPopularity = findViewById(R.id.desc_movie_popularity);
        descTitle = findViewById(R.id.desc_movie_title);
        descVids = findViewById(R.id.desc_btn_video);
        descVids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callServiceVideo();
            }
        });
        String photo = "https://image.tmdb.org/t/p/w500" + result.getPoster_path();
        Glide.with(this)
                .load(photo)
                .into(descPoster);
        descDetail.setText(result.getOverview());
        descReleaseDate.setText(result.getRelease_date());
        descVoteCount.setText(String.valueOf(result.getVote_count()));
        descPopularity.setText(String.valueOf(result.getPopularity()));
        descTitle.setText(result.getOriginal_title());
        descFavory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descFavory.setImageResource(R.drawable.ic_full_star);
                setOnTimeToFavorite();
                refreshFavoriteList();
                Toast.makeText(DetailMovieActivity.this, "Ajouté aux Favoris", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void callServiceVideo() {
        MovieRetrofitApi.MyMovieRetrofitService service = MovieRetrofitApi.getInstance().getClient().create(MovieRetrofitApi.MyMovieRetrofitService.class);
        Call<RootVideo> calls = service.getRootVids(Integer.valueOf(result.getId()), "aba6bb2b364392551140adc1bc89437b");
        Log.d("id", String.valueOf(result.getId()));
        calls.enqueue(new Callback<RootVideo>() {
            @Override
            public void onResponse(Call<RootVideo> call, Response<RootVideo> response) {
                if (response.code() == 200) {
                    processVideoResponse(response);
                    RepositoryMovies.getInstance().toCreateVideoHyperlinkById("youtube");
                    toDisplayVideo();
                }
            }

            @Override
            public void onFailure(Call<RootVideo> call, Throwable t) {
                Toast.makeText(DetailMovieActivity.this, "Echec", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processVideoResponse(Response<RootVideo> response) {
        if ((response.body().getResults() != null)&&(response.body().getResults().size()>0)){
            RepositoryMovies.getInstance().listVideoMovies = (ArrayList<ResultVideo>) response.body().getResults();
            Toast.makeText(this, "Taille de List Result OK", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Result null", Toast.LENGTH_SHORT).show();
        }
    }

    public void toDisplayVideo(){
        if(RepositoryMovies.getInstance().toCreateVideoHyperlinkById("youtube") != null){
            Intent intent = new Intent(DetailMovieActivity.this, VideoMovieActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Pas de video à montrer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshFavoriteList();
        checkIfFavoriteRecipe();
    }

    private void checkIfFavoriteRecipe() {
        if (RepositoryMovies.getInstance().isFavorite(result) == true) {
            descFavory.setImageResource(R.drawable.ic_full_star);
        } else {
            descFavory.setImageResource(R.drawable.ic_empty_star);
        }
    }

    private void refreshFavoriteList() {
        detailActivityViewModel.getFavoriteList(DetailMovieActivity.this).observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                RepositoryMovies.getInstance().listFavoriteList = (ArrayList<Result>) results;
            }
        });
    }

    private void setOnTimeToFavorite() {
        detailActivityViewModel.getFavoriteList(DetailMovieActivity.this).observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                RepositoryMovies.getInstance().listFavoriteList = (ArrayList<Result>) results;
                if(RepositoryMovies.getInstance().isFavorite(result)==true){
                    Toast.makeText(DetailMovieActivity.this, "Deja Favori", Toast.LENGTH_SHORT).show();
                }else{
                    detailActivityViewModel.addToFavorite(result,DetailMovieActivity.this);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
        }
        finish();
        return super.onOptionsItemSelected(item);
    }
}
