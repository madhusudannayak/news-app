package com.example.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.newsapp.NewsViewModel
import com.example.newsapp.R
import com.example.newsapp.adapter.AdapterClickListeners
import com.example.newsapp.adapter.NewsPagingAdapter
import com.example.newsapp.retrofits.response.Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.view.*

@AndroidEntryPoint
class NewsFragment : Fragment(), AdapterClickListeners {
    private val viewModel by viewModels<NewsViewModel>()

    private val newsPagingAdapter = NewsPagingAdapter(this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.list.observe(viewLifecycleOwner) {
            newsPagingAdapter.submitData(lifecycle, it)
        }
        newsPagingAdapter.addLoadStateListener { state ->
            when (state.refresh) {
                is LoadState.Loading -> {
                    view.news_progress.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    view.news_progress.visibility = View.GONE

                }
                is LoadState.Error -> {
                    view.news_progress.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_SHORT).show()

                }
            }
        }
        view.news_recycler.adapter = newsPagingAdapter
    }

    override fun clickListeners(article: Article) {
        findNavController().navigate(R.id.action_newsFragment_to_detailsFragment, bundleOf("article" to article))
    }

}