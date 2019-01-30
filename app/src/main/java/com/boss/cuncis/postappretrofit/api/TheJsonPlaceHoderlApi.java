package com.boss.cuncis.postappretrofit.api;

import com.boss.cuncis.postappretrofit.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TheJsonPlaceHoderlApi {

    @POST("posts")
    Call<User> getResult(@Body User user);
}
