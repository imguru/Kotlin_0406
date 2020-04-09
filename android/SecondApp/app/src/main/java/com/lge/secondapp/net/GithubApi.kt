package com.lge.secondapp.net

import com.lge.secondapp.model.GithubUser
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Retrofit
// 1. API interface 정의
interface GithubApi {
    // "https://api.github.com /users/$username"
    //  HTTP METHOD: GET, POST, PUT, DELETE, PATCH ....

    @GET("/users/{username}")
    fun getGithubUser(@Path("username") username: String): Call<GithubUser>


    // https://api.github.com /search/repositories ?q=Kotlin&page=1&per_page=3
    @GET("/search/repositories")
    fun searchRepo(
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    )
}

// Java에서는 API 객체를 싱글톤으로 주로 사용했습니다.
// 하지만 코틀린에서는 싱글톤 보다는 전역 객체를 통해 처리하는 것이 일반적입니다.

// 2. Retrofit 객체 생성
val githubApi: GithubApi = Retrofit.Builder().apply {

    baseUrl("https://api.github.com")
    client(OkHttpClient())
    addConverterFactory(GsonConverterFactory.create())

}.build().create(GithubApi::class.java)