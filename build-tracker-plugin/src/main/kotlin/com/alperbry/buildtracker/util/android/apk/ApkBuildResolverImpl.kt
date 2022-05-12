package com.alperbry.buildtracker.util.android.apk

import com.alperbry.buildtracker.apk.ApkDataSource
import com.alperbry.buildtracker.data.android.AndroidBuildOutputInfo
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver

class ApkBuildResolverImpl(
    private val source: ApkDataSource
) : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildOutputInfo {
        val sdkDirectory = extension.sdkDirectory
        val apkDirectory = extension.variant.outputFiles.first() // fixme for null safety

        return AndroidBuildOutputInfo(
            id = source.applicationId(
                sdkDirectory,
                apkDirectory
            ),
            moduleName = extension.module,
            versionCode = source.versionCode(
                sdkDirectory,
                apkDirectory
            ),
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
