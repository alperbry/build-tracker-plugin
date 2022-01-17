package com.alperbry.buildtracker.cache

import com.alperbry.buildtracker.data.BuildEnvironmentMetadata
import com.alperbry.buildtracker.data.BuildInfo
import com.alperbry.buildtracker.data.VersionControlMetadata
import com.alperbry.buildtracker.data.android.AndroidBuildInfo

interface BuildInformationCache <T : BuildInfo> {

    var environmentData: BuildEnvironmentMetadata?

    val outputList: MutableList<T>
}


object AndroidBuildInformationCache : BuildInformationCache<AndroidBuildInfo> {

    override var environmentData: BuildEnvironmentMetadata? = null

    override val outputList: MutableList<AndroidBuildInfo> = mutableListOf()
}
