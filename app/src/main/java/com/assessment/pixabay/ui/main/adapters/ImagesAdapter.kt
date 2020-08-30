package com.assessment.pixabay.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.pixabay.BR
import com.assessment.pixabay.data.model.image.ImageModel
import com.assessment.pixabay.databinding.ItemImageBinding

class ImagesAdapter(private val imageList: List<ImageModel>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    lateinit var onItemClicked: OnItemClicked

    interface OnItemClicked{
        fun onImageClicked(model: ImageModel)
    }

    class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(model: ImageModel){
            binding.setVariable(BR.model, model)
            binding.text.text = "Last Selected on "
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList.get(position))
        holder.binding.image.setOnClickListener(View.OnClickListener {
            if (this::onItemClicked.isInitialized)
                onItemClicked.onImageClicked(imageList.get(position))
        })
    }
}