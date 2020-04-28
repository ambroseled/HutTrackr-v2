package com.seng440.ajl190.huttrackr.model

data class HutResponse(
    val assetId: Int,
    val name: String,
    val region: String,
    val status: String,
    val x: Int,
    val y: Int
)