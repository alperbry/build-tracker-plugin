package com.alperbry.buildtracker.cache

import com.alperbry.buildtracker.data.BuildEnvironmentMetadata
import com.alperbry.buildtracker.data.BuildInfo
import com.alperbry.buildtracker.data.ProjectInfo
import com.alperbry.buildtracker.data.android.AndroidBuildInfo

interface BuildInformationCache <T : BuildInfo> {

    var projectInfo: ProjectInfo?

    var environmentData: BuildEnvironmentMetadata?

    val outputList: MutableList<T> // fixme thread safe list

    fun dispose() {
        environmentData = null
        outputList.clear()
    }
}


class AndroidBuildInformationCache : BuildInformationCache<AndroidBuildInfo> {

    override var projectInfo: ProjectInfo? = null

    override var environmentData: BuildEnvironmentMetadata? = null

    override val outputList: MutableList<AndroidBuildInfo> = mutableListOf()
}
