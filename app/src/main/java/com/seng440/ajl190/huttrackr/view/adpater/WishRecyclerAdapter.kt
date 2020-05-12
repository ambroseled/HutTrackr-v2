package com.seng440.ajl190.huttrackr.view.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.WishItem
import com.seng440.ajl190.huttrackr.databinding.WishListItemBinding
import com.seng440.ajl190.huttrackr.utils.listener.WishListClickListener

class WishRecyclerAdapter (
    private val huts: List<WishItem>,
    private val listClickListener: WishListClickListener
) : RecyclerView.Adapter<WishRecyclerAdapter.WishRecyclerViewHolder>() {

    override fun getItemCount() = huts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WishRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.wish_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WishRecyclerViewHolder, position: Int) {
        holder.wishListItemBinding.wishItem = huts[position]
        holder.wishListItemBinding.wishSwitchHutList.setOnClickListener {
            listClickListener.onWishListClick(huts[position], holder.wishListItemBinding.wishSwitchHutList)
        }
        holder.wishListItemBinding.moreInfoIconHut.setOnClickListener {
            listClickListener.onMoreInfoClick(huts[position])
        }
        holder.wishListItemBinding.root.setOnClickListener {
            listClickListener.onHutCardClick(huts[position], holder.wishListItemBinding.root)
        }
    }


    inner class WishRecyclerViewHolder(
        val wishListItemBinding: WishListItemBinding
    ) : RecyclerView.ViewHolder(wishListItemBinding.root)
}