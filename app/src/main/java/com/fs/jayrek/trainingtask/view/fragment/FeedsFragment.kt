package com.fs.jayrek.trainingtask.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.FragmentFeedsBinding
import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.view.adapter.FeedAdapter
import com.fs.jayrek.trainingtask.vmodel.TweetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        swipeRefresh()
    }

    private fun observers() {
        viewModel.getTweets()
        viewModel.tweets.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rView.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.rView.visibility = View.VISIBLE
                    Log.wtf("TWEET0", it.data?.size.toString())
                    if (it.data!!.isNotEmpty()) {
                        binding.rView.apply {
                            layoutManager = LinearLayoutManager(requireActivity())
                            feedAdapter = FeedAdapter(it.data)
                            adapter = feedAdapter
                        }
                    } else {
                        binding.progress.visibility = View.GONE
                        binding.rView.visibility = View.VISIBLE
                        Toast.makeText(
                            requireActivity(),
                            StringConstants.NO_TWEET_AVAILABLE,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(
                        requireActivity(),
                        it.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun swipeRefresh() {
        binding.swipeRefreshLayout.setColorSchemeColors(Color.rgb(0, 0, 0))
        binding.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE)
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}