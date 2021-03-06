package com.seng440.ajl190.huttrackr.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.data.repository.AlertRepository
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import com.seng440.ajl190.huttrackr.data.repository.VisitItemRepository
import com.seng440.ajl190.huttrackr.data.repository.WishItemRepository
import com.seng440.ajl190.huttrackr.utils.lazyDeferred

class HutViewModel(
    private val hutRepository: HutRepository,
    private val visitRepository: VisitItemRepository,
    private val wishItemRepository: WishItemRepository,
    private val alertRepository: AlertRepository
) : ViewModel() {

    private var assetId: Int = 0

    val hut by lazyDeferred {
        hutRepository.getHut(assetId)
    }

    val alerts by lazyDeferred {
        alertRepository.getHutAlerts(assetId)
    }


    fun setHut(hutId: Int) {
        assetId = hutId
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
