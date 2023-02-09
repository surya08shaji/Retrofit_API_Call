package com.example.articlelist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.articlelist.databinding.ArticleListBinding
import com.example.articlelist.model.ShowModelItem
import com.squareup.picasso.Picasso

class ArticleAdapter (private var aList: ArrayList<ShowModelItem>?) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder (val binding: ArticleListBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val binding = ArticleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(aList!![position]) {
                binding.title.text = this.title
                binding.content.text = this.content
                binding.author.text = this.author
                Picasso.with(binding.image.context).load(this.image).into(binding.image)

                binding.share.setOnClickListener {
                    val share = Intent(Intent.ACTION_SEND)
                    share.type = "text/plain"
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                    share.putExtra(Intent.EXTRA_TEXT, url)
                    binding.share.context.startActivity(Intent.createChooser(share, "Share link!"))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return aList?.size ?: 0
    }
}