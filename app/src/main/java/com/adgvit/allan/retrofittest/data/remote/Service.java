package com.adgvit.allan.retrofittest.data.remote;

import com.adgvit.allan.retrofittest.data.model.AnswerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Allan on 27-07-2017.
 */

public interface Service {

    @GET("events")
    Call<AnswerResponse> getAnswers();
    @POST("events")
    Call<AnswerResponse> savePost(@Field("Name") String name,
                                  @Field("Description") String des,
                                  @Field("Organiser") String org);
}
