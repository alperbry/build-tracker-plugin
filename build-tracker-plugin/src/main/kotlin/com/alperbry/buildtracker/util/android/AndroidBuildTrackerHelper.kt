package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.AndroidProjectType.APPLICATION
import com.alperbry.buildtracker.data.android.AndroidProjectType.LIBRARY
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.extension.extension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

class ApplicationBuildTrackerHelper(
    private val mapper: AndroidVariantMapper
) : AndroidPluginHelper<BuildTrackerAndroidExtensions> {

    override fun withExtensions(project: Project, block: (BuildTrackerAndroidExtensions) -> Unit) {
        val extension = extension<AppExtension>(project)
        extension.applicationVariants.all { appVariant ->
            BuildTrackerAndroidExtensions(
                sdkDirectory = extension.sdkDirectory,
                variant = mapper.map(variant = appVariant),
                projectType = APPLICATION
            ).let(block)
        }
    }
}

class LibraryBuildTrackerHelper(
    private val mapper: AndroidVariantMapper
) : AndroidPluginHelper<BuildTrackerAndroidExtensions> {

    override fun withExtensions(project: Project, block: (BuildTrackerAndroidExtensions) -> Unit) {
        val extension = extension<LibraryExtension>(project)
        extension.libraryVariants.all { libVariant ->
            BuildTrackerAndroidExtensions(
                sdkDirectory = extension.sdkDirectory,
                variant = mapper.map(variant = libVariant),
                projectType = LIBRARY
            ).let(block)
        }
    }
}
