package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.data.GradleProjectType
import com.alperbry.buildtracker.data.android.BaseModuleExtension
import com.alperbry.buildtracker.util.extension.ExtensionExtractor
import com.alperbry.buildtracker.util.extension.ModuleExtensionExtractor

interface ModuleExtensionExtractorProvider {
    fun extensionExtractor(type: GradleProjectType): ExtensionExtractor<out BaseModuleExtension>
}

class ModuleExtensionExtractorProviderImpl(
    private val androidDependencyProvider: AndroidDependencyProvider = AndroidDependencyProviderImpl()
) : ModuleExtensionExtractorProvider {

    override fun extensionExtractor(type: GradleProjectType): ExtensionExtractor<out BaseModuleExtension> {
        return when (type) {
            is GradleProjectType.Android -> androidDependencyProvider.extensionExtractor(type.projectType)
            else -> ModuleExtensionExtractor()
        }
    }
}
