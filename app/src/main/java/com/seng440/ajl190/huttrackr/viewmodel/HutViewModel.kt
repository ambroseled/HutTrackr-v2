package com.seng440.ajl190.huttrackr.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class HutViewModel(
    private val hutRepository: HutRepository
) : ViewModel() {

    private var assetId: Int = 0

    val hut by lazyDeferred {
        hutRepository.getHut(assetId)
    }


    fun setHut(hutId: Int) {
        assetId = hutId
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(view.context)
                    .load(imageUrl)
                    .into(view)
            }
        }
    }



}
