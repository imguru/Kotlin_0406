package com.lge.secondapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lge.secondapp.model.Repo
import com.lge.secondapp.model.RepoSearchResponse
import com.lge.secondapp.net.githubApi
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_search_repo.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val adapter = SearchRepoAdapter()
        searchRecyclerView.adapter = adapter

        searchButton.setOnClickListener {
            val text = searchEditText.text.toString()
            if (text.isBlank())
                return@setOnClickListener

            githubApi.searchRepo(text).enqueue(object : Callback<RepoSearchResponse> {
                override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                    Toast.makeText(
                        this@SearchActivity,
                        "Failed - ${t.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<RepoSearchResponse>,
                    response: Response<RepoSearchResponse>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }

                    val items = response.body()?.items ?: emptyList()
                    adapter.items = items
                    // adapter.notifyDataSetChanged() // 데이터 업데이트가 필요하다.
                }
            })


        }

    }
}

class SearchRepoAdapter : RecyclerView.Adapter<SearchRepoAdapter.ViewHolder>() {
    // var items = emptyList<Repo>()

    // var items: List<Repo> by Delegates.observable(emptyList()) { _, _, _ ->
    //    notifyDataSetChanged()
    // }

    var items = emptyList<Repo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search_repo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = items[position]

        // holder.itemView.nameTextView.text = "Name - $position"
        // holder.itemView.repoNameTextView.text = "Repo - $position"

        with(holder.itemView) {
            nameTextView.text = repo.owner.login
            repoNameTextView.text = repo.fullName

            Glide.with(this)
                .load(repo.owner.avatarUrl)
                .into(avatarImageView)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    /*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_repo, parent, false)
    )
    */
}