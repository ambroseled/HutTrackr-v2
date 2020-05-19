package com.seng440.ajl190.huttrackr.data.model

data class HutAlertResponse(
    val alerts: List<Alert>,
    val assetId: Int,
    val name: String
)