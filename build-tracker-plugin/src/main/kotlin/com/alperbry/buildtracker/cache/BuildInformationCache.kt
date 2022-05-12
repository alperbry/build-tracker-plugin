package com.alperbry.buildtracker.cache

import com.alperbry.buildtracker.data.BuildEnvironmentMetadata
import com.alperbry.buildtracker.data.BuildOutputInfo
import com.alperbry.buildtracker.data.GradleProjectBuildInfo
import com.alperbry.buildtracker.data.HardwareInformation
import com.alperbry.buildtracker.data.OperatingSystem
import com.alperbry.buildtracker.data.OperatingSystemInformation
import com.alperbry.buildtracker.data.ProjectInfo
import com.alperbry.buildtracker.data.android.AndroidBuildOutputInfo
import com.alperbry.buildtracker.util.timer.Timer

interface BuildInformationCache <T : BuildOutputInfo> : BuildInformationState {

    var projectInfo: ProjectInfo?

    var environmentData: BuildEnvironmentMetadata?

    val outputList: MutableList<T> // fixme thread safe list

    fun dispose() {
        environmentData = null
        outputList.clear()
    }
}


class AndroidBuildInformationCache : BuildInformationCache<AndroidBuildOutputInfo> {

    override var projectInfo: ProjectInfo? = null

    override var environmentData: BuildEnvironmentMetadata? = null

    override val outputList: MutableList<AndroidBuildOutputInfo> = mutableListOf()

    override fun valid() = outputList.isNotEmpty()

    override fun snapshot(timer: Timer): GradleProjectBuildInfo? {

        return projectInfo?.let {
            GradleProjectBuildInfo(
                it.id,
                it.stateIdentifier,
                timer.duration(),
                environmentData?.hardwareMetadata ?: HardwareInformation("", 0L, "", 0),
                environmentData?.osMetadata ?: OperatingSystemInformation(OperatingSystem.OTHER, "", ""),
                outputList.map {
                    mapOf(
                        "id" to it.id,
                        "moduleName" to it.moduleName,
                        "versionCode" to it.versionCode as Any,
                        "flavorName" to it.flavorName,
                        "buildType" to it.buildType
                    )
                }
            )
        }
    }
}
