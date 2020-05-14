package com.seng440.ajl190.huttrackr.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.data.repository.TrackRepository
import com.seng440.ajl190.huttrackr.data.repository.VisitItemRepository
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class TrackViewModel(
    private val trackRepository: TrackRepository,
    private val visitRepository: VisitItemRepository,
    private val wishItemRepository: WishItemRepository
) : ViewModel() {

    private var assetId: String = ""

    val track by lazyDeferred {
        trackRepository.getTrack(assetId)
    }

    fun setTrack(trackId: String) {
        assetId = trackId
    }

    fun saveVisit(visitItem: VisitItem) {
        visitRepository.insertVisitItem(visitItem)
    }

    fun saveWish(wishItem: WishItem) {
        wishItemRepository.insertWishItem(wishItem)
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
