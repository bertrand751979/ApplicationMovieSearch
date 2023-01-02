package com.example.applicationmoviesearch.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.repository.RepositoryMovies;

public class VideoMovieActivity extends AppCompatActivity {
    private WebView webVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Videos");
        webVideo=findViewById(R.id.web_view_video);
        webVideo.setWebViewClient(new WebViewClient());
        webVideo.getSettings().setJavaScriptEnabled(true);
        webVideo.loadUrl(RepositoryMovies.getInstance().toCreateVideoHyperlinkById("youtube"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
