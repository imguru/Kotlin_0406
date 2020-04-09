package com.lge.secondapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            // Retrofit 의 Callback은 Main Thread에서 수행된다.
            // => UI의 변경 등을 수행해도 된다.
            val call = githubApi.getGithubUser("mabo")
            call.enqueue(object : Callback<GithubUser> {
                override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<GithubUser>,
                    response: Response<GithubUser>
                ) {
                    if (!response.isSuccessful)
                        return

                    val user = response.body() ?: return
                    Log.e("XXX", "user - $user")

                    Glide.with(this@SecondActivity)
                        .load(user.avatarUrl)
                        .into(imageView)

                    Toast.makeText(
                        this@SecondActivity,
                        user.name,
                        Toast.LENGTH_SHORT
                    ).show()


                }
            })
        }

    }
}

// Retrofit
// 1. API interface 정의
interface GithubApi {
    // "https://api.github.com /users/$username"
    //  HTTP METHOD: GET, POST, PUT, DELETE, PATCH ....

    @GET("/users/{username}")
    fun getGithubUser(@Path("username") username: String): Call<GithubUser>

}

// Java에서는 API 객체를 싱글톤으로 주로 사용했습니다.
// 하지만 코틀린에서는 싱글톤 보다는 전역 객체를 통해 처리하는 것이 일반적입니다.

// 2. Retrofit 객체 생성
val githubApi: GithubApi = Retrofit.Builder().apply {

    baseUrl("https://api.github.com")
    client(OkHttpClient())
    addConverterFactory(GsonConverterFactory.create())

}.build().create(GithubApi::class.java)























