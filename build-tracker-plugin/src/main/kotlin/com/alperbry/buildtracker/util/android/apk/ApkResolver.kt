package com.alperbry.buildtracker.util.android.apk

import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions

interface ApkResolver {

    fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildMetadata
}
