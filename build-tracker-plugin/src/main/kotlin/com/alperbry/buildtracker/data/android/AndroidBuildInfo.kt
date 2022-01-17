package com.alperbry.buildtracker.data.android

import com.alperbry.buildtracker.data.BuildInfo

data class AndroidBuildInfo(
    override val id: String,
    override val stateIdentifier: String,
    val versionCode: Int?,
    val flavorName: String,
    val buildType: String
) : BuildInfo()
