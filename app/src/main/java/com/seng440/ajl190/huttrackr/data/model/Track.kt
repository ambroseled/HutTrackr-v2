package com.seng440.ajl190.huttrackr.data.model

data class Track(
    val assetId: String,
    val distance: String,
    val dogsAllowed: String,
    val introduction: String,
    val introductionThumbnail: String,
    val locationString: String,
    val name: String,
    val region: List<String>,
    val staticLink: String,
    val walkDuration: String,
    val x: Double,
    val y: Double
)