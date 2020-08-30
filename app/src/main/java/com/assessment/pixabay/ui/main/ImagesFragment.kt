package com.assessment.pixabay.ui.main

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.pixabay.BaseFragment
import com.assessment.pixabay.Constants
import com.assessment.pixabay.R
import com.assessment.pixabay.data.model.image.ImageModel
import com.assessment.pixabay.databinding.FragmentImagesBinding
import com.assessment.pixabay.ui.main.adapters.ImagesAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ImagesFragment : BaseFragment<FragmentImagesBinding>(), ImagesAdapter.OnItemClicked {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ImagesAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_images
    }

    override fun initView() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        subscribeObservers()
        viewModel.fetchImages()
    }

    private fun subscribeObservers() {


        viewModel.images.observe(this, Observer {images ->
            images?.let {
                adapter = ImagesAdapter(it)
                adapter.onItemClicked = this
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.adapter = adapter
            }
        })
    }

    override fun onImageClicked(model: ImageModel) {
        Glide.with(this)
                .asBitmap()
                .load(model.largeImageURL)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                        saveToLocal(bitmap, model)
                    }

                })
    }

    private fun saveToLocal(bitmap: Bitmap, model: ImageModel) {
        val wrapper = ContextWrapper(context)
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)

        file = File(file, "${model.id.toString()}.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            val pref = activity?.getSharedPreferences("mypreferences", Context.MODE_PRIVATE)
            pref?.edit()?.putString(Constants.IMAGE_PATH, file.path)?.putString(Constants.IMAGE_ID, model.id.toString())?.apply()
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            model.lastSelected = currentDate
            viewModel.update(model)
            Toast.makeText(activity,"Image Selected", Toast.LENGTH_SHORT).show()

        } catch (e: IOException){ // Catch the exception
            e.printStackTrace()
        }

    }

    fun saveImageSelected(){

    }

}