package com.example.articlelist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.articlelist.activities.NextActivity
import com.example.articlelist.databinding.ListRowBinding
import com.example.articlelist.model.DataModelItem
import com.squareup.picasso.Picasso


class Adapter(private var mList: ArrayList<DataModelItem>?) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder (val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val binding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        with(holder) {
            with(mList!![position]) {
                binding.title.text = this.title
                binding.author.text = this.author

                Picasso.with(binding.image.context).load(this.image).into(binding.image)
                binding.subRecyclerView.layoutManager = LinearLayoutManager(binding.subRecyclerView.context)
//                binding.subRecyclerView.adapter = SubAdapter(this.category_list)

                binding.cardView.setOnClickListener {
                    val intent = Intent(binding.image.context, NextActivity::class.java)
                    intent.putExtra("key",id)
                    binding.image.context.startActivity(intent)
                }

//                binding.cardView.setOnClickListener {
//                    if (binding.subRecyclerView.visibility == View.VISIBLE) {
//                        binding.subRecyclerView.visibility = View.GONE
//                    } else {
//                        binding.subRecyclerView.visibility = View.VISIBLE
//                    }
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }
}



































//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ListRowBinding.inflate(inflater)
//        return ViewHolder(binding)
//
////        val view = LayoutInflater.from(parent.context)
////            .inflate(R.layout.list_row, parent, false)
////        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mList!![position])
//
//
//    class ViewHolder(private val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: DataModelItem) {
//
//            binding.item = item
//            binding.executePendingBindings()
//
//            binding.subRecyclerView.layoutManager = LinearLayoutManager(binding.subRecyclerView.context)
//            binding.subRecyclerView.adapter = SubAdapter(this.category_list)
//
//            binding.cardView.setOnClickListener {
//
//                if (binding.subRecyclerView.visibility == View.VISIBLE) {
//                    binding.subRecyclerView.visibility = View.GONE
//                } else {
//                    binding.subRecyclerView.visibility = View.VISIBLE
//                }
////                val intent = Intent(binding.image.context, NextActivity::class.java)
////                intent.putExtra("key", ArrayList<DataModelItem>())
////                binding.image.context.startActivity(intent)
//
////                val intent = Intent(binding.subIcon.context,NextActivity::class.java)
////                intent.putExtra("key",term_id)
////                binding.subIcon.context.startActivity(intent)
//
////                val intent = Intent(this@DataEntryActivity, MainActivity::class.java)
////                intent.putExtra("first",firstName)
////                intent.putExtra("last",lastName)
////                startActivity(intent)
//            }
//
////            with(binding) {
////                title.text = item.title
////                content.text = item.content
////                author.text = item.author
////                Picasso.with(image.context).load(item.image).into(image)
//            }
//        }
//
////        val title: TextView = itemView.findViewById(R.id.title)
////        val content: TextView = itemView.findViewById(R.id.content)
////        val image: ImageView = itemView.findViewById(R.id.image)
////        val author: TextView = itemView.findViewById(R.id.author)
//
//
////        val items = mList?.get(position)
////        if (items != null) {
////            holder.title.text = items.title
////            holder.content.text = items.content
////            holder.author.text = items.author
////
////            Picasso.with(holder.image.context)
////                .load(items.image)
////                .into(holder.image)
////
////        }
////    }
//
//    override fun getItemCount(): Int {
//        return mList?.size ?: 0
//    }
//}