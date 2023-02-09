package com.example.articlelist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.articlelist.activities.NextActivity
import com.example.articlelist.databinding.SubListBinding
import com.example.articlelist.model.CategoryX
import com.squareup.picasso.Picasso

class SubAdapter(private var subList: List<CategoryX>) : RecyclerView.Adapter<SubAdapter.SubViewHolder>() {
    class SubViewHolder (val binding: SubListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubAdapter.SubViewHolder {
        val binding = SubListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubAdapter.SubViewHolder, position: Int) {
        with(holder) {
            with(subList[position]) {
                binding.subName.text = this.name
                Picasso.with(binding.subIcon.context).load(this.icon).into(binding.subIcon)

                binding.cardView.setOnClickListener {
                    val intent = Intent(binding.subIcon.context, NextActivity::class.java)
                    intent.putExtra("key",term_id)
                    binding.subIcon.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return subList?.size ?: 0
    }
}