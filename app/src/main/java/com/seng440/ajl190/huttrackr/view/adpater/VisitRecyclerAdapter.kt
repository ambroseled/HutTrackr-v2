package com.seng440.ajl190.huttrackr.view.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.VisitItem
import com.seng440.ajl190.huttrackr.databinding.VisitItemBinding
import com.seng440.ajl190.huttrackr.utils.listener.VisitClickListener

class VisitRecyclerAdapter (
    private val visits: List<VisitItem>,
    private val listClickListener: VisitClickListener
) : RecyclerView.Adapter<VisitRecyclerAdapter.VisitRecyclerViewHolder>() {

    override fun getItemCount() = visits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VisitRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.visit_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VisitRecyclerViewHolder, position: Int) {
        holder.visitItemBinding.visit = visits[position]
        holder.visitItemBinding.wishSwitchVisit.setOnClickListener {
            listClickListener.onWishListClick(visits[position], holder.visitItemBinding.wishSwitchVisit)
        }
        holder.visitItemBinding.moreInfoIconVisit.setOnClickListener {
            listClickListener.onMoreInfoClick(visits[position])
        }
        holder.visitItemBinding.root.setOnClickListener {
            listClickListener.onVisitCardClick(visits[position], holder.visitItemBinding.root)
        }
    }


    inner class VisitRecyclerViewHolder(
        val visitItemBinding: VisitItemBinding
    ) : RecyclerView.ViewHolder(visitItemBinding.root)
}