package com.alperbry.buildtracker.data.android

import java.io.File

// Per each variant
data class BuildTrackerAndroidExtensions(
    val sdkDirectory: File,
    val variant: Variant,
    val projectType: AndroidProjectType
)

data class Variant(
    val variantName: String,
    val flavorName: String,
    val assembleTask: String,
    val outputFiles: List<File>
)
