package com.alperbry.buildtracker.util.android.apk

import com.alperbry.buildtracker.apk.ApkDataSource
import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import com.alperbry.buildtracker.util.vcs.VCSInfoResolver

class ApkBuildResolverImpl(
    private val source: ApkDataSource,
    private val vcsInfoResolver: VCSInfoResolver
) : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildInfo {
        val sdkDirectory = extension.sdkDirectory
        val apkDirectory = extension.variant.outputFiles.first() // todo fixme bundle support needed

        return AndroidBuildInfo(
            id = source.applicationId(
                sdkDirectory,
                apkDirectory
            ),
            stateIdentifier = vcsInfoResolver.stateIdentifier(),
            versionCode = source.versionCode(
                sdkDirectory,
                apkDirectory
            ),
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
