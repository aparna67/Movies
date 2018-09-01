
package com.example.aparna.movies.api;

import android.os.AsyncTask;

import java.net.URL;
import java.util.List;

import com.example.aparna.movies.BuildConfig;
import com.example.aparna.movies.data.Movie;
import com.example.aparna.movies.data.MovieCollection;
import com.example.aparna.movies.ui.MoviesAdapter;


public class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

    private MoviesAdapter adapter;

    public FetchMoviesTask(MoviesAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        URL moviesRequestUrl = NetworkUtils.buildUrl(BuildConfig.API_KEY, params[0]);
        try {
            String jsonMoviesResponse = NetworkUtils
                    .getResponseFromHttpUrl(moviesRequestUrl);
            MovieCollection movieCollection = MoviesJsonUtils.parseJson(jsonMoviesResponse);
            return movieCollection.getMovies();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies != null) {
            adapter.setMoviesData(movies);
        }
    }
}
