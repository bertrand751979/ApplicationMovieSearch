package com.example.applicationmoviesearch.fragments;

import static com.example.applicationmoviesearch.activities.MainActivity.MOVIE_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmoviesearch.ActionDeleteMovieFavory;
import com.example.applicationmoviesearch.ActionToGoToDetail;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.activities.DetailMovieActivity;
import com.example.applicationmoviesearch.adapters.FavoriteAdapter;
import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.repository.RepositoryMovies;
import com.example.applicationmoviesearch.viewModels.FavoriteMovieListFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesListFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private FavoriteMovieListFragmentViewModel favoriteMovieListFragmentViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Favoris:");
        favoriteMovieListFragmentViewModel = new ViewModelProvider(this).get(FavoriteMovieListFragmentViewModel.class);
        Toast.makeText(FavoriteMoviesListFragment.this.getContext(), "Size"+RepositoryMovies.getInstance().listFavoriteList.size(), Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_list_movie,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_display_favorite_list);
        ActionToGoToDetail actionToGoToDetail = new ActionToGoToDetail() {
            @Override
            public void goToDetail(Result result) {
                Intent intent = new Intent(FavoriteMoviesListFragment.this.getContext(), DetailMovieActivity.class);
                intent.putExtra(MOVIE_EXTRA,result);
                startActivity(intent);
                Toast.makeText(FavoriteMoviesListFragment.this.getContext(), "Vers Description", Toast.LENGTH_SHORT).show();
            }
        };
        ActionDeleteMovieFavory actionDeleteMovieFavory = new ActionDeleteMovieFavory() {
            @Override
            public void deleteFavory(Result result) {
                favoriteMovieListFragmentViewModel.deleteMovieFav(result,getContext());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteAdapter = new FavoriteAdapter(actionToGoToDetail,actionDeleteMovieFavory);
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favoriteAdapter);
        favoriteMovieListFragmentViewModel.getFavoriteList(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                favoriteAdapter.setListFavoriteAdapter(new ArrayList<>(results));
                if (results.size() == 0) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    EmptyFavoryListFragment llf = new EmptyFavoryListFragment();
                    ft.replace(R.id.fragment_container, llf);
                    ft.commit();
                }

                RepositoryMovies.getInstance().recipeAlreadyFavory = (new ArrayList<>(results));
            }
        });
    }
}
