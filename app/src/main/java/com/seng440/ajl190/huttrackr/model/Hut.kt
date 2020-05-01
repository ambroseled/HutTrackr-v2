package com.seng440.ajl190.huttrackr.model

data class Hut(
    val assetId: Int,
    val bookable: Boolean,
    val facilities: List<String>,
    val hutCategory: String,
    val introduction: String,
    val introductionThumbnail: String,
    val locationString: String,
    val name: String,
    val numberOfBunks: Int,
    val place: String,
    val proximityToRoadEnd: Any,
    val region: String,
    val staticLink: String,
    val status: String,
    val x: Int,
    val y: Int
)