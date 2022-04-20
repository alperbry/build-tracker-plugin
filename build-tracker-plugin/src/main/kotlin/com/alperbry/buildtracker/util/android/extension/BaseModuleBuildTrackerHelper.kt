package com.alperbry.buildtracker.util.android.extension

import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.extension.ExtensionExtractor
import com.alperbry.buildtracker.util.android.AndroidVariantMapper
import com.alperbry.buildtracker.util.extension.androidExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project

class BaseAppModuleExtensionExtractor(
    private val mapper: AndroidVariantMapper
) : ExtensionExtractor<BuildTrackerAndroidExtensions> {

    override fun withExtensions(project: Project, block: (BuildTrackerAndroidExtensions) -> Unit) {
        val extension = androidExtension<BaseAppModuleExtension>(project)

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
