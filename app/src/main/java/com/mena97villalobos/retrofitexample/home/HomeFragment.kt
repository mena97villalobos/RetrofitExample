package com.mena97villalobos.retrofitexample.home

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mena97villalobos.retrofitexample.R
import com.mena97villalobos.retrofitexample.databinding.HomeFragmentBinding
import com.mena97villalobos.retrofitexample.network.Post

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_fragment,
                container,
                false
        )

        binding.getAll.setOnClickListener {
            viewModel.getAllPosts()
        }
        binding.getPost1.setOnClickListener {
            viewModel.getPostById(1)
        }
        binding.post.setOnClickListener {
            viewModel.insertPost(Post(userId = 1, title = "Test Post", body = "Test Post"))
        }

        setupObservers()

        return binding.root
    }


    private fun setupObservers() {

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            binding.infoView.text = it.toString()
        })

        viewModel.postById.observe(viewLifecycleOwner, Observer {
            binding.infoView.text = it.toString()
        })

        viewModel.postInserted.observe(viewLifecycleOwner, Observer {
            binding.infoView.text = it.toString()
        })
    }


}