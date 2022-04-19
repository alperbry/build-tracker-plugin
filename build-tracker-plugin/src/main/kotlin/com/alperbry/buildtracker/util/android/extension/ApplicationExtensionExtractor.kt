package com.alperbry.buildtracker.util.android.extension

import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.ExtensionExtractor
import com.alperbry.buildtracker.util.android.AndroidVariantMapper
import com.alperbry.buildtracker.util.extension.extension
import com.android.build.gradle.AppExtension
import org.gradle.api.Project
import org.gradle.api.UnknownDomainObjectException

class ApplicationExtensionExtractor(
    private val mapper: AndroidVariantMapper,
    private val baseAppModuleExtensionExtractor: BaseAppModuleExtensionExtractor
) : ExtensionExtractor<BuildTrackerAndroidExtensions> {

    override fun withExtensions(project: Project, block: (BuildTrackerAndroidExtensions) -> Unit) {
        try {
            baseAppModuleExtensionExtractor.withExtensions(project, block)
        } catch (e: UnknownDomainObjectException) {
            // Unless it is the base module of app with dynamic features
            val extension = extension<AppExtension>(project)
            extension.applicationVariants.all { appVariant ->
                BuildTrackerAndroidExtensions(
                    sdkDirectory = extension.sdkDirectory,
                    module = project.name,
                    variant = mapper.map(variant = appVariant),
                    projectType = AndroidProjectType.APPLICATION
                ).let(block)
            }
        }
    }
}
