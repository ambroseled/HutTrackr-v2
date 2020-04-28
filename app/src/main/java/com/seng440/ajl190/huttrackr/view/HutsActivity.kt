package com.seng440.ajl190.huttrackr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.seng440.ajl190.huttrackr.R
import com.seng440.ajl190.huttrackr.repository.HutRespository
import com.seng440.ajl190.huttrackr.utils.DocApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HutsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huts)
    }
}
