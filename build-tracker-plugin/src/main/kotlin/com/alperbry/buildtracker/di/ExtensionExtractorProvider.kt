package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.data.GradleProjectType
import com.alperbry.buildtracker.data.android.BaseProjectExtension
import com.alperbry.buildtracker.util.android.ExtensionExtractor
import com.alperbry.buildtracker.util.extension.ProjectExtensionExtractor

interface ExtensionExtractorProvider {
    fun extensionExtractor(type: GradleProjectType): ExtensionExtractor<out BaseProjectExtension>
}

class ExtensionExtractorProviderImpl(
    private val androidDependencyProvider: AndroidDependencyProvider = AndroidDependencyProviderImpl()
) : ExtensionExtractorProvider {

    override fun extensionExtractor(type: GradleProjectType): ExtensionExtractor<out BaseProjectExtension> {
        return when (type) {
            is GradleProjectType.Android -> androidDependencyProvider.extensionExtractor(type.projectType)
            else -> ProjectExtensionExtractor()
        }
    }
}
