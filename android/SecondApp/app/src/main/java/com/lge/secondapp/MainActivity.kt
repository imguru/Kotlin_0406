package com.lge.secondapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*


// OkHttp의 Callback을 익명 객체를 통해 사용하는 것이 불편합니다.
// 함수를 통해 처리할 수 있는 확장 함수를 제공합니다.
inline fun Call.enqueue(
    crossinline onResponse: (response: Response) -> Unit,
    crossinline onFailure: (e: IOException) -> Unit
) {
    enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            onFailure(e)
        }

        override fun onResponse(call: Call, response: Response) {
            onResponse(response)
        }
    })
}


// Github API 연동 -> Github APP
// 1) OkHttp + Gson
// 2) Retrofit + Gson
//   Retrofit
//   : HTTP API를 사용할 때 반복적으로 발생하는 코드를 자동으로 생성하는 기술

// 3) Retrofit + Gson + Rx
class MainActivity : AppCompatActivity() {

    // val client2 = OkHttpClient()
    private val client: OkHttpClient by lazy {
        OkHttpClient()
    }

    private val gson: Gson by lazy {
        Gson()
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            // 1. OkHttp
            val client = OkHttpClient()
            val request = Request.Builder().apply {
                url("https://api.github.com/users/JakeWharton")
            }.build()

            val call = client.newCall(request)
            // call?
            //  사용자가 동기로 호출할지, 비동기로 호출할지를 결정할 수 있도록 하였다.

            // * 비동기 - enqueue()
            //  : ThreadPool이 처리하는 큐에 작업을 집어넣는다.
            // => 비동기는 결과의 완료 시점에 처리해야 하는 작업을 콜백을 통해 처리한다.
            call.enqueue(onResponse = { response ->
                if (!response.isSuccessful)
                    return@enqueue

                val json = response.body?.string()
                json?.let {
                    // Log.e("XXX", "json - $json")

                    // Gson 사용법
                    // val gson = Gson()
                    // "2009-03-24T16:09:53Z"
                    val gson = GsonBuilder().apply {
                    }.create()

                    val user = gson.fromJson(json, GithubUser::class.java)
                    Log.e("XXX", "user - $user")
                }

                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "Success", Toast.LENGTH_SHORT
                    ).show()
                }
            }, onFailure = { e ->

            })

            /*
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful)
                        return

                    val json = response.body?.string()
                    json?.let {
                        Log.e("XXX", "json - $json")
                    }

                    // 주의: 비동기콜에서 콜백의 수행의 컨텍스트는 별도의 스레드에서 수행됩니다.
                    // => UI를 업데이트 해야 하는 작업은 반드시 UiThread(Main Thread)를 통해
                    //    처리되어야 합니다.
                    // Android가 제공하는 헬퍼 함수
                    runOnUiThread {
                        Toast.makeText(this@MainActivity,
                                "Success", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            */


            // * 동기 - execute()
            //  1. 메인 스레드에서 네트워크 요청이 동기로 수행할 경우 예외가 발생합니다.
            //  => NetworkOnMainThread
            //  2. 인터넷에 대한 퍼미션이 필요합니다.
            //  => AndroidManifest.xml
            /*
            Thread {
                val response = call.execute()
                // HTTP 요청 - statusCode
                // 200: 성공
                // 400: 클라이언트 오류
                // 500: 서버 오류
                // if (response.code in 200..299) {
                if (!response.isSuccessful) {
                    return@Thread
                }

                val body = response.body
                body?.let {
                    Log.e("XXX", "body - ${body.string()}")
                }
            }.start()
            */
        }
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            getGithubUser("JakeWharton", { user ->
                runOnUiThread {
                    Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    // HTTP 요청에 대한 코드는 정형화되어 있다.
    // 요청에 대한 코드를 자동으로 생성하는 기술 - Retrofit 라이브러리
    //  Java - AOP(관점 지향 프로그래밍)
    //  => '어노테이션'을 이용한 코드 생성 기술


    fun getGithubUser(
        username: String,
        onSuccess: (GithubUser) -> Unit,
        onFailure: ((String) -> Unit)? = null
    ) {

        val request = Request.Builder().apply {
            url("https://api.github.com/users/$username")
        }.build()

        val call = client.newCall(request)
        call.enqueue(onResponse = { response ->
            if (!response.isSuccessful) {
                onFailure?.invoke("HTTP 요청 실패 - ${response.code}")
                return@enqueue
            }

            val json = response.body?.string()

            val user = json?.let {
                gson.fromJson(json, GithubUser::class.java)
            }

            if (user == null)
                onFailure?.invoke("Empty JSON error")
            else
                onSuccess(user)

        }, onFailure = { e ->
            onFailure?.invoke(e.localizedMessage ?: "네트워크 오류")
        })
    }
}


// JSON => DTO
//     Gson(Google + JSON) - KVC
data class GithubUser(
    val login: String,
    val id: Int,
    val name: String?,
    val location: String?,
    val company: String?,
    val blog: String?,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("created_at") val createdAt: Date,
    @field:SerializedName("updated_at") val updatedAt: Date
)

/*
https://api.github.com/users/JakeWharton
{
  "login": "JakeWharton",
  "id": 66577,
  "avatar_url": "https://avatars0.githubusercontent.com/u/66577?v=4",
  "name": "Jake Wharton",
  "company": "Google, Inc.",
  "blog": "http://jakewharton.com",
  "location": "Pittsburgh, PA, USA",
  "created_at": "2009-03-24T16:09:53Z",
  "updated_at": "2020-04-07T13:27:20Z"
}
*/











