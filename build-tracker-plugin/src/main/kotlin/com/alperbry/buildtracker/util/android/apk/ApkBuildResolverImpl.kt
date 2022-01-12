package com.alperbry.buildtracker.util.android.apk

import com.alperbry.buildtracker.apk.ApkRepository
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver

class ApkBuildResolverImpl(
    private val repository: ApkRepository
) : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildMetadata {
        val sdkDirectory = extension.sdkDirectory
        val apkDirectory = extension.variant.outputFiles.first() // todo fixme bundle support needed

        return AndroidBuildMetadata(
            id = repository.applicationId(
                sdkDirectory,
                apkDirectory
            ),
            versionCode = repository.versionCode(
                sdkDirectory,
                apkDirectory
            ),
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
