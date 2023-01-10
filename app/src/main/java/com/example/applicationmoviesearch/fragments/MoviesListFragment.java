package com.example.applicationmoviesearch.fragments;

import static com.example.applicationmoviesearch.activities.MainActivity.MOVIE_EXTRA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmoviesearch.ActionDeleteMovieFavory;
import com.example.applicationmoviesearch.ActionToGoToDetail;
import com.example.applicationmoviesearch.InterfaceEditTextDialog;
import com.example.applicationmoviesearch.MovieRetrofitApi;
import com.example.applicationmoviesearch.MyAlertDialogFragment;
import com.example.applicationmoviesearch.MySecondAlertDialogFragment;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.activities.DetailMovieActivity;
import com.example.applicationmoviesearch.activities.MainActivity;
import com.example.applicationmoviesearch.adapters.FavoriteAdapter;
import com.example.applicationmoviesearch.adapters.OpenFavoryAdapter;
import com.example.applicationmoviesearch.adapters.ResultAdapter;
import com.example.applicationmoviesearch.models.Result;
import com.example.applicationmoviesearch.models.Root;
import com.example.applicationmoviesearch.repository.RepositoryMovies;
import com.example.applicationmoviesearch.viewModels.MoviesListFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewFav;
    private ResultAdapter resultAdapter;
    private OpenFavoryAdapter openFavoryAdapter;
    private GridLayout gridLayout;
    private EditText searchEditText;
    private InterfaceEditTextDialog interfaceEditTextDialog;
    public MoviesListFragmentViewModel movieListFragmentViewModel;
    private ArrayList<Result> listDisplayMovie = new ArrayList<>();
    public MenuItem itemYear;
    public  MenuItem itemMenu;
    private FavoriteAdapter favoriteAdapter;
    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Alert dialog lancer une seul fois
        sharedPreferences = getActivity().getSharedPreferences(MOVIE_EXTRA, 0);
        boolean dialogShown = sharedPreferences.getBoolean("dialogShown", false);
        if (dialogShown!=true) {
            showSecondAlertDialog();
            //Ecrive la valeur
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("dialogShown", true);
            editor.commit();
        }
        movieListFragmentViewModel = new ViewModelProvider(this).get(MoviesListFragmentViewModel.class);
        getActivity().setTitle("Films: ");
        listDisplayMovie= RepositoryMovies.getInstance().getListMovie();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movies_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_display_movie_list);
        recyclerViewFav = view.findViewById(R.id.recyclerViewMyFavory);
        RelativeLayout myLayout = view.findViewById(R.id.main_layout);
        searchEditText = view.findViewById(R.id.edit_query);
        gridLayout = view.findViewById(R.id.raw_open_fav);
        ActionToGoToDetail actionToGoToDetail = new ActionToGoToDetail() {
            @Override
            public void goToDetail(Result result) {
                Intent intent = new Intent(MoviesListFragment.this.getContext(), DetailMovieActivity.class);
                intent.putExtra(MOVIE_EXTRA,result);
                startActivity(intent);
                Toast.makeText(MoviesListFragment.this.getContext(), "Vers Description", Toast.LENGTH_SHORT).show();
            }
        };

        ActionDeleteMovieFavory actionDeleteMovieFavory = new ActionDeleteMovieFavory() {
            @Override
            public void deleteFavory(Result result) {
                movieListFragmentViewModel.deleteMovieFav(result,getContext());
            }
        };

        interfaceEditTextDialog = new InterfaceEditTextDialog() {
            @Override
            public void transfertEditText(String searchText) {
                RepositoryMovies.getInstance().searchMoviesList.clear();
                if(RepositoryMovies.getInstance().choiceYear(searchText)){
                    resultAdapter.setListResultAdapter(RepositoryMovies.getInstance().searchMoviesList);
                }else{
                    resultAdapter.setListResultAdapter(RepositoryMovies.getInstance().listMovie);
                }
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        resultAdapter = new ResultAdapter(actionToGoToDetail);
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resultAdapter);
        movieListFragmentViewModel.liveDataResult.observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultAdapter.setListResultAdapter(new ArrayList<>(results));
            }
        });
        movieListFragmentViewModel.toPostMyMovieList();
        recyclerViewFav.setLayoutManager(new LinearLayoutManager(this.getContext()));
        openFavoryAdapter = new OpenFavoryAdapter(actionToGoToDetail,actionDeleteMovieFavory);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFav.setLayoutManager(layoutManager2);
        //GridLayoutManager layoutManagers=new GridLayoutManager(this.getContext(),4);
        //recyclerViewFav.setLayoutManager(layoutManagers);
        recyclerViewFav.setAdapter(openFavoryAdapter);
        movieListFragmentViewModel.getFavoriteList(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                openFavoryAdapter.setListOpenFavAdapter(new ArrayList<>(results));
                RepositoryMovies.getInstance().recipeAlreadyFavory = (new ArrayList<>(results));
            }
        });




       /* recyclerViewFav.setLayoutManager(new LinearLayoutManager(this.getContext()));
        favoriteAdapter = new FavoriteAdapter(actionToGoToDetail,actionDeleteMovieFavory);
        GridLayoutManager layoutManagers=new GridLayoutManager(this.getContext(),2);
        recyclerViewFav.setLayoutManager(layoutManagers);
        recyclerViewFav.setAdapter(favoriteAdapter);
        movieListFragmentViewModel.getFavoriteList(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                favoriteAdapter.setListFavoriteAdapter(new ArrayList<>(results));
                RepositoryMovies.getInstance().recipeAlreadyFavory = (new ArrayList<>(results));
            }
        });*/


        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int evenType = event.getActionMasked();
                switch(evenType)
                {
                    case MotionEvent.ACTION_UP:
                        closeKeyboard();
                        Log.i("touchEvent","Action up");
                        break;
                }
                return true;
            }
        });


        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    callService();
                    return true;
                }
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void closeKeyboard() {
        View view = this.requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager)getActivity().getSystemService(MoviesListFragment.this.getContext().INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void callService() {
        MovieRetrofitApi.MyMovieRetrofitService service = MovieRetrofitApi.getInstance().getClient().create(MovieRetrofitApi.MyMovieRetrofitService.class);
        Call<Root> call = service.getRoot("aba6bb2b364392551140adc1bc89437b", searchEditText.getText().toString());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.code() == 200) {
                    processResponse(response);
                }
                Toast.makeText(MoviesListFragment.this.getContext(), "OK", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(MoviesListFragment.this.getContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processResponse(Response<Root> response) {
        if(response.body().getResults().size()>0){
            RepositoryMovies.getInstance().listMovie= (ArrayList<Result>) response.body().getResults();
            Intent intent = new Intent(MoviesListFragment.this.getContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        itemMenu=menu.findItem(R.id.menu_icon);
        itemYear = menu.findItem(R.id.search_year);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_icon:
                return (true);

            case R.id.search_year:
                showAlertDialog();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
    private void showAlertDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        MyAlertDialogFragment alertDialog = MyAlertDialogFragment.newInstance("Some title");
        alertDialog.setInterfaceEditTextDialog(interfaceEditTextDialog);
        alertDialog.show(fm, "fragment_alert");
    }

    private void showSecondAlertDialog() {
        FragmentManager fmo = getActivity().getSupportFragmentManager();
        MySecondAlertDialogFragment alertDialogFragment = MySecondAlertDialogFragment.newInstance("some title");
        alertDialogFragment.show(fmo, "fragment_alert");
    }
}
