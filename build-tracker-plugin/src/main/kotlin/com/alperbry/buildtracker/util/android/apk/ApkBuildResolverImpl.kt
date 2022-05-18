package com.alperbry.buildtracker.util.android.apk

import com.alperbry.buildtracker.data.android.AndroidBuildOutputInfo
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.util.android.AndroidBuildResolver

class ApkBuildResolverImpl : AndroidBuildResolver {

    override fun buildInfo(extension: BuildTrackerAndroidExtensions): AndroidBuildOutputInfo {
        val apkDirectory = extension.variant.outputFiles.first() // fixme for null safety
        return AndroidBuildOutputInfo(
            id = extension.variant.id,
            moduleName = extension.module,
            versionCode = extension.variant.versionCode,
            flavorName = extension.variant.flavorName,
            buildType = extension.variant.variantName
        )
    }
}
