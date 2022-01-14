package com.alperbry.buildtracker.apk

import java.io.File

interface ApkDataSource {

    fun applicationId(sdkDirectory: File, apkDirectory: File): String

    fun versionCode(sdkDirectory: File, apkDirectory: File): Int
}
