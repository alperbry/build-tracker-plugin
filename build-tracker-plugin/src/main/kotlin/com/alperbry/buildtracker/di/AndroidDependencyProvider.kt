package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.util.android.AndroidExtensionMapper
import com.alperbry.buildtracker.util.android.AndroidExtensionMapperImpl
import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.AndroidProjectType.APPLICATION
import com.alperbry.buildtracker.data.android.AndroidProjectType.FEATURE
import com.alperbry.buildtracker.data.android.AndroidProjectType.LIBRARY
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidPluginHelper
import com.alperbry.buildtracker.util.android.ApplicationBuildTrackerHelper
import com.alperbry.buildtracker.util.android.LibraryBuildTrackerHelper

class AndroidDependencyProvider {

    private val extensionMapper: AndroidExtensionMapper by lazy {
        AndroidExtensionMapperImpl()
    }

    fun buildTrackerHelper(type: AndroidProjectType): AndroidPluginHelper<BuildTrackerAndroidExtensions> {
        return when (type) {
            APPLICATION -> ApplicationBuildTrackerHelper(extensionMapper)
            LIBRARY -> LibraryBuildTrackerHelper(extensionMapper)
            FEATURE -> LibraryBuildTrackerHelper(extensionMapper)
        }
    }
}
