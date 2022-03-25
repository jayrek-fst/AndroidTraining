package com.fs.jayrek.trainingtask.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fs.jayrek.trainingtask.databinding.ItemTweetBinding
import com.fs.jayrek.trainingtask.model.model.TweetModel


class FeedAdapter(): RecyclerView.Adapter<FeedAdapter.MyViewHolder>() {

    private var _itemTweetModel = mutableListOf<TweetModel>()

    fun listFeed(list: List<TweetModel>){
        _itemTweetModel = list.toMutableList()
    }

    inner class MyViewHolder (private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TweetModel){
            binding.tweet = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val adapterBinding = ItemTweetBinding.inflate(inflater, parent, false)
        return MyViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(_itemTweetModel[position])
    }

    override fun getItemCount(): Int = _itemTweetModel.size

}

