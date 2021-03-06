package com.example.amit.movieapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MovieAsyncTask extends AsyncTask<Void, Void,Void> {
    MovieApp context;

    public MovieAsyncTask(MovieApp context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.e("MovieAsyncTask!!", "Trigger Adapter");

        // trigger adapter;
        context.movieAdapter.notifyDataSetChanged();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        MovieInfo movieInfo;
        URL url;
        try {
            if (Settings.selection_types == Selection_Types.Selection_Types_Popularity) {
                url = MovieDbUtils.buildPopularURL();
            } else {
                url = MovieDbUtils.buildHighestRatedURL();
            }
            String response = MovieDbUtils.getResponseFromHttpUrl(url);
            JSONObject obj = new JSONObject(response);
            JSONArray result = obj.getJSONArray("results");
            MovieDB.movieInfoArrayList.clear();

            Log.e("AMit SIZE!!", String.valueOf(result.length()));
            for (int i = 0; i < result.length(); i++) {
                try {
                    JSONObject oneObject = result.getJSONObject(i);

                    // Pulling items from the array
                    movieInfo = new MovieInfo();
                    movieInfo.path = oneObject.getString("poster_path");
                    movieInfo.release_date = oneObject.getString("release_date");
                    movieInfo.title = oneObject.getString("title");
                    movieInfo.vote_average = oneObject.getString("vote_average");
                    movieInfo.overview = oneObject.getString("overview");
                    MovieDB.movieInfoArrayList.add(movieInfo);

                    if (i == 0)
                    {
                        Log.e("!!PATH", movieInfo.path);
                    }
                } catch (JSONException e) {
                    // Oops
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
