package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.extension.extension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

class ApplicationBuildTrackerHelper(
    private val mapper: AndroidExtensionMapper
) : AndroidPluginHelper<BuildTrackerAndroidExtensions> {

    override fun withExtensions(project: Project, block: (BuildTrackerAndroidExtensions) -> Unit) {
        extension<AppExtension>(project).applicationVariants.all { variant ->
            mapper.map(variant).let(block)
        }
    }
}

class LibraryBuildTrackerHelper(
    private val mapper: AndroidExtensionMapper
) : AndroidPluginHelper<BuildTrackerAndroidExtensions> {

    override fun withExtensions(project: Project, block: (BuildTrackerAndroidExtensions) -> Unit) {
        extension<LibraryExtension>(project).libraryVariants.all { variant ->
            mapper.map(variant).let(block)
        }
    }
}
