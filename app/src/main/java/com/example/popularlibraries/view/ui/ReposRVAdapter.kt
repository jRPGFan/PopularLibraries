package com.example.popularlibraries.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.databinding.ReposUserBinding
import com.example.popularlibraries.presentation.IUserRepoListPresenter
import com.example.popularlibraries.view.UserReposView

class ReposRVAdapter(private val presenter: IUserRepoListPresenter) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ReposUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ).apply {
        itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
    }

    override fun onBindViewHolder(holder: ReposRVAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })


    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(private val viewBinding: ReposUserBinding) :
        RecyclerView.ViewHolder(viewBinding.root),
        UserReposView {
        override var pos = -1
        override fun setRepo(repo: String) {
            viewBinding.repoName.text = repo
        }
    }
}