package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions

interface AndroidBuildResolver {

    fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildInfo
}
