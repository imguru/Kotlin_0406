package com.lge.secondapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.lge.secondapp.model.Repo
import kotlinx.android.synthetic.main.item_search_repo.view.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

    }
}

class SearchRepoAdapter : RecyclerView.Adapter<SearchRepoAdapter.ViewHolder>() {
    var items = emptyList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search_repo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // val repo = items[position]

        // holder.itemView.nameTextView.text = "Name - $position"
        // holder.itemView.repoNameTextView.text = "Repo - $position"

        with(holder.itemView) {
            nameTextView.text = "Name - $position"
            repoNameTextView.text = "Repo - $position"
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