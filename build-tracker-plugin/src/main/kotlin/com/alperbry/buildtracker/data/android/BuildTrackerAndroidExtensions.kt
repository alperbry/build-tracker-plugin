package com.alperbry.buildtracker.data.android

import java.io.File

interface BaseProjectExtension {
    val assembleTask: String
}

// Per each variant
data class BuildTrackerAndroidExtensions(
    val sdkDirectory: File,
    val module: String,
    val variant: Variant,
    val projectType: AndroidProjectType
) : BaseProjectExtension {
    override val assembleTask: String
        get() = variant.assembleTask
}

data class Variant(
    val variantName: String,
    val flavorName: String,
    val assembleTask: String,
    val outputFiles: List<File>
)
