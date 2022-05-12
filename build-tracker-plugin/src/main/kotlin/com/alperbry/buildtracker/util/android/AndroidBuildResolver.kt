package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.AndroidBuildOutputInfo
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions

interface AndroidBuildResolver {

    fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildOutputInfo
}
