package com.omrobbie.cataloguemovieuiux.api;

import com.omrobbie.cataloguemovieuiux.model.now_playing.NowPlayingModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by omrobbie on 09/10/2017.
 */

public interface APICall {

    @GET("movie/now_playing")
    Call<NowPlayingModel> getNowPlayingMovie();
}
