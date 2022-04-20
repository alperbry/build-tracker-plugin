package com.alperbry.buildtracker.data.android

import java.io.File

interface BaseModuleExtension {
    val assembleTask: String
}

// Per each variant
data class BuildTrackerAndroidExtensions(
    val sdkDirectory: File,
    val module: String,
    val variant: Variant,
    val projectType: AndroidProjectType
) : BaseModuleExtension {
    override val assembleTask: String
        get() = variant.assembleTask
}

data class Variant(
    val variantName: String,
    val flavorName: String,
    val assembleTask: String,
    val outputFiles: List<File>
)
