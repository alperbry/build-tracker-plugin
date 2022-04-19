package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.AndroidProjectType.APPLICATION
import com.alperbry.buildtracker.data.android.AndroidProjectType.FEATURE
import com.alperbry.buildtracker.data.android.AndroidProjectType.LIBRARY
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.ExtensionExtractor
import com.alperbry.buildtracker.util.android.AndroidVariantMapper
import com.alperbry.buildtracker.util.android.AndroidVariantMapperImpl
import com.alperbry.buildtracker.util.android.extension.ApplicationExtensionExtractor
import com.alperbry.buildtracker.util.android.extension.BaseAppModuleExtensionExtractor
import com.alperbry.buildtracker.util.android.extension.AndroidLibraryExtensionExtractor

interface AndroidDependencyProvider {

    fun extensionExtractor(type: AndroidProjectType): ExtensionExtractor<BuildTrackerAndroidExtensions>
}

class AndroidDependencyProviderImpl : AndroidDependencyProvider {

    private val variantMapper: AndroidVariantMapper by lazy {
        AndroidVariantMapperImpl()
    }

    override fun extensionExtractor(type: AndroidProjectType): ExtensionExtractor<BuildTrackerAndroidExtensions> {
        return when (type) {
            APPLICATION -> ApplicationExtensionExtractor(variantMapper, BaseAppModuleExtensionExtractor(variantMapper))
            LIBRARY -> AndroidLibraryExtensionExtractor(variantMapper)
            FEATURE -> ApplicationExtensionExtractor(variantMapper, BaseAppModuleExtensionExtractor(variantMapper))
        }
    }
}
