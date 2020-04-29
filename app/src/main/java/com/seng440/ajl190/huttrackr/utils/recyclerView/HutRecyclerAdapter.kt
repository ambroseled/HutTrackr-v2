package com.seng440.ajl190.huttrackr.utils.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.databinding.HutListItemBinding
import com.seng440.ajl190.huttrackr.model.HutResponse

class HutRecyclerAdapter (
    private val huts: List<HutResponse>,
    private val listClickListener: HutListClickListener
) : RecyclerView.Adapter<HutRecyclerAdapter.HutRecyclerViewHolder>() {

    override fun getItemCount() = huts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HutRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.hut_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HutRecyclerViewHolder, position: Int) {
        holder.hutListItemBinding.hutResponse = huts[position]
        holder.hutListItemBinding.wishSwitchHutList.setOnClickListener {
            listClickListener.onWishListClick(huts[position])
        }
        holder.hutListItemBinding.moreInfoIcon.setOnClickListener {
            listClickListener.onMoreInfoClick(huts[position])
        }
        holder.hutListItemBinding.root.setOnClickListener {
            listClickListener.onHutCardClick(huts[position], holder.hutListItemBinding.root)
        }
    }


    inner class HutRecyclerViewHolder(
        val hutListItemBinding: HutListItemBinding
    ) : RecyclerView.ViewHolder(hutListItemBinding.root)
}