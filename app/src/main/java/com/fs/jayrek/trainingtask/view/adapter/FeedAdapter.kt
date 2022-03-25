package com.fs.jayrek.trainingtask.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fs.jayrek.trainingtask.databinding.ItemTweetBinding
import com.fs.jayrek.trainingtask.model.model.TweetModel


class FeedAdapter(private var _itemTweetModel: List<TweetModel>): RecyclerView.Adapter<FeedAdapter.MyViewHolder>() {

    inner class MyViewHolder (private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TweetModel){
            binding.tweet = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemTweetBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(_itemTweetModel[position])
    }

    override fun getItemCount(): Int = _itemTweetModel.size

}

