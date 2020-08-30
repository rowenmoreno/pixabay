package com.assessment.pixabay.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.assessment.pixabay.BaseFragment
import com.assessment.pixabay.Constants
import com.assessment.pixabay.R
import com.assessment.pixabay.data.model.image.ImageModel
import com.assessment.pixabay.databinding.FragmentHomeBinding
import com.assessment.pixabay.ui.main.adapters.ImagesAdapter
import com.bumptech.glide.Glide

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: ImagesAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        getSelectedImage()
    }

    fun getSelectedImage(){
        val prefs = activity?.getSharedPreferences("mypreferences", Context.MODE_PRIVATE)
        val path = prefs?.getString(Constants.IMAGE_PATH,"")
        Glide.with(this)
                .load(path)
                .into(binding.image)
    }

}