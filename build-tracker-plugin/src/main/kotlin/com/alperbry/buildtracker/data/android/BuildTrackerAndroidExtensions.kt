package com.alperbry.buildtracker.data.android

import java.io.File

data class BuildTrackerAndroidExtensions(
    val outputFiles: List<File>,
    val assembleTask: String
)
