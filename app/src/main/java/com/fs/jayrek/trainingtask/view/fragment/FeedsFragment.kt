package com.fs.jayrek.trainingtask.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.FragmentFeedsBinding
import com.fs.jayrek.trainingtask.view.adapter.FeedAdapter
import com.fs.jayrek.trainingtask.vmodel.TweetViewModel

class FeedsFragment : Fragment() {

    private lateinit var binding: FragmentFeedsBinding
    private lateinit var feedAdapter: FeedAdapter

    private val viewModel: TweetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_feeds, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers(){
        viewModel.getTweets()
        viewModel.tweets.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                binding.rView.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                feedAdapter = FeedAdapter(it)
                adapter = feedAdapter }
            } else {
                Log.wtf("EMPTY", "empty")
            }
        }
    }
}