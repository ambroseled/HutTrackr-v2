package com.seng440.ajl190.huttrackr.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.seng440.ajl190.huttrackr.data.model.Hut
import com.seng440.ajl190.huttrackr.data.repository.HutRepository
import kotlinx.coroutines.Job

class HutViewModel(
    private val hutRepository: HutRepository
) : ViewModel() {


    private lateinit var job: Job
    private val _hut = MutableLiveData<Hut>()
    val hut: LiveData<Hut>
        get() = _hut

    fun getHut(assetId: Int?) {
        if (assetId != null) {

        } else {
            // todo handle this
        }

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
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
