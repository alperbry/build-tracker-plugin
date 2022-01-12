package com.alperbry.buildtracker.apk

import com.alperbry.buildtracker.util.commandline.CommandLineExecutor
import java.io.File

class ApkRepositoryImpl(
    private val commandLineExecutor: CommandLineExecutor
) : ApkRepository {

    override fun applicationId(sdkDirectory: File, apkDirectory: File): String {
        val apkAnalyzer = sdkDirectory.routeToApkAnalyzer()

        return commandLineExecutor.execute(
            apkAnalyzer.absolutePath,
            "manifest",
            "application-id",
            apkDirectory.absolutePath
        ).trim()
    }

    override fun packageName(sdkDirectory: File, apkDirectory: File): String {
        val apkAnalyzer = sdkDirectory.routeToApkAnalyzer()

        return commandLineExecutor.execute(
            apkAnalyzer.absolutePath,
            "manifest",
            "application-id",
            apkDirectory.absolutePath
        ).trim()
    }

    override fun versionCode(sdkDirectory: File, apkDirectory: File): Int {
        val apkAnalyzer = sdkDirectory.routeToApkAnalyzer()

        return commandLineExecutor.execute(
            apkAnalyzer.absolutePath,
            "manifest",
            "version-code",
            apkDirectory.absolutePath
        ).trim().toInt()
    }

    private fun File.routeToApkAnalyzer() = File(this, ROUTE_TO_APK_ANALYZER)
}

private const val ROUTE_TO_APK_ANALYZER = "/tools/bin/apkanalyzer"
