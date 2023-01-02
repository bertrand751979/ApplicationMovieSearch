package com.example.applicationmoviesearch.fragments;

import static com.example.applicationmoviesearch.activities.MainActivity.ACTOR_EXTRA;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applicationmoviesearch.ActionGoToDetailActor;
import com.example.applicationmoviesearch.MovieRetrofitApi;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.activities.DetailActorMovieHistoryActivity;
import com.example.applicationmoviesearch.adapters.ActorAdapter;
import com.example.applicationmoviesearch.models.KnownFor;
import com.example.applicationmoviesearch.models.ResultActor;
import com.example.applicationmoviesearch.models.RootActor;
import com.example.applicationmoviesearch.repository.RepositoryMovies;
import com.example.applicationmoviesearch.viewModels.ActorMoviesListFragmentViewModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorMoviesListFragment extends Fragment {
    private EditText searchActorText;
    private RecyclerView recyclerViewActorList;
    private ActorAdapter actorAdapter;
    public ActorMoviesListFragmentViewModel actorMoviesListFragmentViewModel;

    //private KnownFor knownFor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Acteur");
        actorMoviesListFragmentViewModel = new ViewModelProvider(this).get(ActorMoviesListFragmentViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actor_movie, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewActorList = view.findViewById(R.id.recycler_view_actor_movie_list);
        searchActorText = view.findViewById(R.id.edit_actor_query);
        RelativeLayout myLayout = view.findViewById(R.id.mainActorLayout);
        recyclerViewActorList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ActionGoToDetailActor actionGoToDetailActor = new ActionGoToDetailActor() {
            @Override
            public void goToHistoryActorMovies(ResultActor resultActor) {
                Intent intent = new Intent(ActorMoviesListFragment.this.getContext(), DetailActorMovieHistoryActivity.class);
                intent.putExtra(ACTOR_EXTRA,resultActor);
                startActivity(intent);
            }
        };

        actorAdapter = new ActorAdapter(actionGoToDetailActor);
        recyclerViewActorList.setAdapter(actorAdapter);
        actorMoviesListFragmentViewModel.liveDataActor.observe(getViewLifecycleOwner(), new Observer<List<ResultActor>>() {
            @Override
            public void onChanged(List<ResultActor> resultActors) {
                actorAdapter.setListAdapterActor(new ArrayList<>(resultActors));
            }
        });
        actorMoviesListFragmentViewModel.toPostListActor();


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

        searchActorText.setOnKeyListener(new View.OnKeyListener() {
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
            InputMethodManager manager = (InputMethodManager)getActivity().getSystemService(ActorMoviesListFragment.this.getContext().INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void callService() {
        MovieRetrofitApi.MyMovieRetrofitService service = MovieRetrofitApi.getInstance().getClient().create(MovieRetrofitApi.MyMovieRetrofitService.class);
        Call<RootActor> call = service.getRootActor("aba6bb2b364392551140adc1bc89437b", searchActorText.getText().toString());
        call.enqueue(new Callback<RootActor>() {
            @Override
            public void onResponse(Call<RootActor> call, Response<RootActor> response) {
                if (response.code() == 200) {
                    processResponse(response);
                }
                Toast.makeText(ActorMoviesListFragment.this.getContext(), "OK", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RootActor> call, Throwable t) {
                Toast.makeText(ActorMoviesListFragment.this.getContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processResponse(Response<RootActor> response) {
        if(response.body().getResults().size()>0){
            RepositoryMovies.getInstance().actorMoviesList= (ArrayList<ResultActor>) response.body().getResults();
            actorMoviesListFragmentViewModel.toPostListActor();
        }
    }











}
