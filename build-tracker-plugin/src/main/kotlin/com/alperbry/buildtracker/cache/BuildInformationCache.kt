package com.alperbry.buildtracker.cache

import com.alperbry.buildtracker.data.BuildEnvironmentMetadata
import com.alperbry.buildtracker.data.VersionControlMetadata
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata

interface BuildInformationCache <T> {

    var environmentData: BuildEnvironmentMetadata?

    var vcsData: VersionControlMetadata?

    val outputList: MutableList<T>

    var durationInMs: Long
}


object AndroidBuildInformationCache : BuildInformationCache<AndroidBuildMetadata> {

    override var environmentData: BuildEnvironmentMetadata? = null

    override var vcsData: VersionControlMetadata? = null

    override val outputList: MutableList<AndroidBuildMetadata> = mutableListOf()

    override var durationInMs: Long = 0
}
