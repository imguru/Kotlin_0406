package com.lge.secondapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lge.secondapp.model.User
import com.lge.secondapp.net.githubApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            // Retrofit 의 Callback은 Main Thread에서 수행된다.
            // => UI의 변경 등을 수행해도 된다.
            val call = githubApi.getGithubUser("mabo")
            call.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
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