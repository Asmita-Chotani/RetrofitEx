package com.adgvit.allan.retrofittest.data.remote;

import com.adgvit.allan.retrofittest.data.model.AnswerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Allan on 27-07-2017.
 */

public interface Service {

    @GET("events")
    Call<AnswerResponse> getAnswers();
}
