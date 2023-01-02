package com.example.applicationmoviesearch;
import com.example.applicationmoviesearch.models.Root;
import com.example.applicationmoviesearch.models.RootActor;
import com.example.applicationmoviesearch.models.RootVideo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MovieRetrofitApi {
    public interface MyMovieRetrofitService {
        @GET("search/movie")
        Call<Root> getRoot(@Query("api_key")String api_key, @Query("query")String query);

        @GET("movie/{movie_id}/videos")
         Call<RootVideo> getRootVids(@Path ("movie_id")Integer movie_id, @Query("api_key")String api_key);

        @GET("search/person")
        Call<RootActor> getRootActor(@Query("api_key")String api_key,@Query("query")String query);
    }
    private final static String BASE_URL="https://api.themoviedb.org/3/";

    private MovieRetrofitApi(){}
    private static MovieRetrofitApi INSTANCE = null;
    public static MovieRetrofitApi getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MovieRetrofitApi();
        }
        return INSTANCE;
    }

    public Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
