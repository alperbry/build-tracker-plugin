package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.AndroidProjectType.APPLICATION
import com.alperbry.buildtracker.data.android.AndroidProjectType.FEATURE
import com.alperbry.buildtracker.data.android.AndroidProjectType.LIBRARY
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidPluginHelper
import com.alperbry.buildtracker.util.android.AndroidVariantMapper
import com.alperbry.buildtracker.util.android.AndroidVariantMapperImpl
import com.alperbry.buildtracker.util.android.ApplicationBuildTrackerHelper
import com.alperbry.buildtracker.util.android.LibraryBuildTrackerHelper

interface AndroidDependencyProvider {

    fun buildTrackerHelper(type: AndroidProjectType): AndroidPluginHelper<BuildTrackerAndroidExtensions>
}

class AndroidDependencyProviderImpl : AndroidDependencyProvider {

    private val variantMapper: AndroidVariantMapper by lazy {
        AndroidVariantMapperImpl()
    }

    override fun buildTrackerHelper(type: AndroidProjectType): AndroidPluginHelper<BuildTrackerAndroidExtensions> {
        return when (type) {
            APPLICATION -> ApplicationBuildTrackerHelper(variantMapper)
            LIBRARY -> LibraryBuildTrackerHelper(variantMapper)
            FEATURE -> LibraryBuildTrackerHelper(variantMapper)
        }
    }
}
