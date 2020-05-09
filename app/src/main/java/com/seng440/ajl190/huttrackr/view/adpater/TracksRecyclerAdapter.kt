package com.seng440.ajl190.huttrackr.view.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.data.model.TrackResponse
import com.seng440.ajl190.huttrackr.databinding.TrackListItemBinding
import com.seng440.ajl190.huttrackr.utils.listener.TrackListClickListener

class TracksRecyclerAdapter (
    private val tracks: List<TrackResponse>,
    private val listClickListener: TrackListClickListener
) : RecyclerView.Adapter<TracksRecyclerAdapter.TrackRecyclerViewHolder>() {


    override fun getItemCount() = tracks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TrackRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.track_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TrackRecyclerViewHolder, position: Int) {
        holder.trackListItemBinding.trackResponse = tracks[position]
        holder.trackListItemBinding.wishSwitchTrackList.setOnClickListener {
            listClickListener.onWishListClick(tracks[position])
        }
        holder.trackListItemBinding.moreInfoIconTrack.setOnClickListener {
            listClickListener.onMoreInfoClick(tracks[position])
        }
        holder.trackListItemBinding.root.setOnClickListener {
            listClickListener.onTrackCardClick(tracks[position], holder.trackListItemBinding.root)
        }
    }


    inner class TrackRecyclerViewHolder(
        val trackListItemBinding: TrackListItemBinding
    ) : RecyclerView.ViewHolder(trackListItemBinding.root)
}