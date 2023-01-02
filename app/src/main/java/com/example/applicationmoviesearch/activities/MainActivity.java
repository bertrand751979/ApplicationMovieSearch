package com.example.applicationmoviesearch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.applicationmoviesearch.MySecondAlertDialogFragment;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.fragments.ActorMoviesListFragment;
import com.example.applicationmoviesearch.fragments.FavoriteMoviesListFragment;
import com.example.applicationmoviesearch.fragments.MoviesListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    public static String MOVIE_EXTRA = "movie_extra";
    public static String ACTOR_EXTRA = "actor_extra";
    public static String HISTORY_MOVIE_EXTRA = "history_movie_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav =findViewById(R.id.botton_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoviesListFragment()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_movie_list);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_movie_list:
                    selectedFragment = new MoviesListFragment();
                    break;

                case R.id.nav_favorite_list:
                    selectedFragment = new FavoriteMoviesListFragment();
                    break;

                case R.id.nav_actor_movie:
                    selectedFragment = new ActorMoviesListFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };


}