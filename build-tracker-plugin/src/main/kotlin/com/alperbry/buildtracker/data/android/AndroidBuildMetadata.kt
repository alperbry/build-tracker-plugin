package com.alperbry.buildtracker.data.android

data class AndroidBuildMetadata(
    val applicationId: String,
    val versionCode: Int?,
    val flavorName: String,
    val buildType: String
)
