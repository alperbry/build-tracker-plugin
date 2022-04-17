package com.alperbry.buildtracker.util.android.extension

import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidPluginHelper
import com.alperbry.buildtracker.util.android.AndroidVariantMapper
import com.alperbry.buildtracker.util.extension.extension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

class LibraryBuildTrackerHelper(
    private val mapper: AndroidVariantMapper
) : AndroidPluginHelper<BuildTrackerAndroidExtensions> {

    override fun withExtensions(project: Project, block: (BuildTrackerAndroidExtensions) -> Unit) {
        val extension = extension<LibraryExtension>(project)

        extension.libraryVariants.all { libVariant ->
            BuildTrackerAndroidExtensions(
                sdkDirectory = extension.sdkDirectory,
                module = project.name,
                variant = mapper.map(variant = libVariant),
                projectType = AndroidProjectType.LIBRARY
            ).let(block)
        }
    }
}
