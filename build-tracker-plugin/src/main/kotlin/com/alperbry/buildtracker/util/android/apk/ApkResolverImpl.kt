package com.alperbry.buildtracker.util.android.apk

import com.alperbry.buildtracker.apk.ApkRepository
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions

class ApkResolverImpl(
    private val repository: ApkRepository
) : ApkResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildMetadata {
        val sdkDirectory = extension.sdkDirectory
        val apkDirectory = extension.variant.outputFiles.first() // todo fixme bundle support needed

        return AndroidBuildMetadata(
            applicationId = repository.applicationId(
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
