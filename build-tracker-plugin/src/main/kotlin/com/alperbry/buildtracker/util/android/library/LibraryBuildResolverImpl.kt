package com.alperbry.buildtracker.util.android.library

import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import com.alperbry.buildtracker.util.android.id.ProjectIdGenerator

class LibraryBuildResolverImpl(
    private val idGenerator: ProjectIdGenerator
) : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildMetadata {
        return AndroidBuildMetadata(
            id = idGenerator.id(),
            versionCode = -1,
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
