package com.example.prototype;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("login.php")
    @FormUrlEncoded
    Call<User> login(@Field("email") String username, @Field("password") String password);

    @POST("plan.php")
    @FormUrlEncoded
    Call<List<Planner>> plan(@Field("email") String email);
}