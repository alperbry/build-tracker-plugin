package com.alperbry.buildtracker.util.android.library

import com.alperbry.buildtracker.data.android.AndroidBuildOutputInfo
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import com.alperbry.buildtracker.util.android.id.ProjectIdGenerator

class LibraryBuildResolverImpl(
    private val idGenerator: ProjectIdGenerator
) : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildOutputInfo {
        return AndroidBuildOutputInfo(
            id = idGenerator.id(),
            moduleName = extension.module,
            versionCode = -1,
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
