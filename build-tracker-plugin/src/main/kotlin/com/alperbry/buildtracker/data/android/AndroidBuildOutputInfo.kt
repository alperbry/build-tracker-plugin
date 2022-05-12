package com.alperbry.buildtracker.data.android

import com.alperbry.buildtracker.data.BuildOutputInfo

data class AndroidBuildOutputInfo(
    override val id: String,
    override val moduleName: String,
    val versionCode: Int?,
    val flavorName: String,
    val buildType: String
) : BuildOutputInfo
