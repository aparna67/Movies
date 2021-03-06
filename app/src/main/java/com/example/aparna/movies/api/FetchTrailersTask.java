

package com.example.aparna.movies.api;

import android.os.AsyncTask;

import java.net.URL;
import java.util.List;

import com.example.aparna.movies.BuildConfig;
import com.example.aparna.movies.data.Trailer;
import com.example.aparna.movies.data.TrailerCollection;



public abstract class FetchTrailersTask extends AsyncTask<String, Void, List<Trailer>> {

    private static final String LOG_TAG = FetchTrailersTask.class.getSimpleName();
    private String id;

    public FetchTrailersTask(String id) {
        this.id = id;
    }

    @Override
    protected List<Trailer> doInBackground(String... params) {
        URL trailersRequestUrl = NetworkUtils.buildTrailersOrReviewsUrl(BuildConfig.API_KEY, id, "videos");
        try {
            String jsonTrailersResponse = NetworkUtils
                    .getResponseFromHttpUrl(trailersRequestUrl);
            TrailerCollection trailerCollection = TrailersJsonUtils.parseJson(jsonTrailersResponse);
            return trailerCollection.getTrailers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
