package com.seng440.ajl190.huttrackr.data.model

data class TrackAlertResponse(
    val alerts: List<Alert>,
    val assetId: String,
    val name: String
)