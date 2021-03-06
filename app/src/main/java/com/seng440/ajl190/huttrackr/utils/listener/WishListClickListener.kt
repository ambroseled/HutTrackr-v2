package com.seng440.ajl190.huttrackr.utils.listener

import android.view.View
import android.widget.Switch
import com.seng440.ajl190.huttrackr.data.model.WishItem

interface WishListClickListener {

    fun onWishListClick(wishItem: WishItem, switch: Switch)

    fun onHutCardClick(wishItem: WishItem, view: View)

    fun onMoreInfoClick(wishItem: WishItem)
}