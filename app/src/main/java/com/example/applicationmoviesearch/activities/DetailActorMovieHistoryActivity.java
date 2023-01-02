package com.example.applicationmoviesearch.activities;

import static com.example.applicationmoviesearch.activities.MainActivity.ACTOR_EXTRA;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.adapters.AdapterHistoryMovie;
import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.models.ResultActor;
import com.example.applicationmoviesearch.repository.RepositoryMovies;
import com.example.applicationmoviesearch.viewModels.DetailActorMovieHistoryActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActorMovieHistoryActivity extends AppCompatActivity {
    private ImageView posterActorMovieHistory;
    private TextView nameActorMovieHistory;
    private ResultActor resultActor;
    private RecyclerView recyclerView;
    private AdapterHistoryMovie adapterHistoryMovie;
    public DetailActorMovieHistoryActivityViewModel detailActorMovieHistoryActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actor_movie_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Filmographie");
        detailActorMovieHistoryActivityViewModel = new  ViewModelProvider(this).get(DetailActorMovieHistoryActivityViewModel.class);
        resultActor = (ResultActor) getIntent().getSerializableExtra(ACTOR_EXTRA);
        posterActorMovieHistory = findViewById(R.id.desc_actor_movie_historyposter);
        recyclerView = findViewById(R.id.recycler_history);
        nameActorMovieHistory = findViewById(R.id.desc_actor_movie_historyname);
        nameActorMovieHistory.setText(resultActor.getName());
        String profilPath ="https://image.tmdb.org/t/p/w500"+resultActor.getProfile_path();
        Glide.with(this)
                .load(profilPath)
                .into(posterActorMovieHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterHistoryMovie = new AdapterHistoryMovie();
        recyclerView.setAdapter(adapterHistoryMovie);

        Log.d("size", String.valueOf(RepositoryMovies.getInstance().listHistoryMovie.size()));

        detailActorMovieHistoryActivityViewModel.liveDataListHistory.observe(this, new Observer<List<KnownFor>>() {
            @Override
            public void onChanged(List<KnownFor> knownFors) {
                adapterHistoryMovie.setListAdapterHistory(new ArrayList<>(knownFors));
            }
        });
        detailActorMovieHistoryActivityViewModel.toPost(resultActor.getKnown_for());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
        }
        finish();
        return super.onOptionsItemSelected(item);
    }
}
