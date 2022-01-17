package com.alperbry.buildtracker.util.android.library

import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import com.alperbry.buildtracker.util.android.id.ProjectIdGenerator
import com.alperbry.buildtracker.util.vcs.VCSInfoResolver

class LibraryBuildResolverImpl(
    private val idGenerator: ProjectIdGenerator,
    private val vcsInfoResolver: VCSInfoResolver
) : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildInfo {
        return AndroidBuildInfo(
            id = idGenerator.id(),
            stateIdentifier = vcsInfoResolver.stateIdentifier(),
            versionCode = -1,
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
