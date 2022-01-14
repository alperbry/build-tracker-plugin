package com.alperbry.buildtracker.util.android.apk

import com.alperbry.buildtracker.apk.ApkDataSource
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver

class ApkBuildResolverImpl(
    private val source: ApkDataSource
) : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildMetadata {
        val sdkDirectory = extension.sdkDirectory
        val apkDirectory = extension.variant.outputFiles.first() // todo fixme bundle support needed

        return AndroidBuildMetadata(
            id = source.applicationId(
                sdkDirectory,
                apkDirectory
            ),
            versionCode = source.versionCode(
                sdkDirectory,
                apkDirectory
            ),
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
