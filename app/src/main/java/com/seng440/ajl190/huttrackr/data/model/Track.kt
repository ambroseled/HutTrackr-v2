package com.seng440.ajl190.huttrackr.data.model

//@Entity(tableName = "track")
data class Track(
    //@PrimaryKey
    //@ColumnInfo(name = "assetId")
    val assetId: String,

    //@ColumnInfo(name = "distance")
    val distance: String,

    //@ColumnInfo(name = "dogsAllowed")
    val dogsAllowed: String,

    //@ColumnInfo(name = "introduction")
    val introduction: String,

    //@ColumnInfo(name = "introductionThumbnail")
    val introductionThumbnail: String,

    //@ColumnInfo(name = "kayakingDuration")
    val kayakingDuration: String,

    //@ColumnInfo(name = "locationArray")
    val locationArray: List<String>,

    //@ColumnInfo(name = "locationString")
    val locationString: String,

    //@ColumnInfo(name = "mtbDuration")
    val mtbDuration: String,

    //@ColumnInfo(name = "mtbDurationCategory")
    val mtbDurationCategory: List<String>,

    //@ColumnInfo(name = "mtbTrackCategory")
    val mtbTrackCategory: List<String>,

    //@ColumnInfo(name = "name")
    val name: String,

    //@ColumnInfo(name = "permittedActivities")
    val permittedActivities: List<String>,

    //@ColumnInfo(name = "region")
    val region: List<String>,

    //@ColumnInfo(name = "staticLink")
    val staticLink: String,

    //@ColumnInfo(name = "walkDuration")
    val walkDuration: String,

    //@ColumnInfo(name = "walkDurationCategory")
    val walkDurationCategory: List<String>,

    //@ColumnInfo(name = "walkTrackCategory")
    val walkTrackCategory: List<String>,

    //@ColumnInfo(name = "wheelchairsAndBuggies")
    val wheelchairsAndBuggies: String,

    //@ColumnInfo(name = "x")
    val x: Double,

    //@ColumnInfo(name = "y")
    val y: Double
)