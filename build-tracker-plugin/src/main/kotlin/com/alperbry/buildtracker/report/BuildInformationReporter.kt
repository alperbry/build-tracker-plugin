package com.alperbry.buildtracker.report

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtracker.util.timer.Timer
import com.alperbry.buildtrackerclient.firebase.BuildTrackerClientProvider
import com.alperbry.buildtrackerclient.model.HardwareMetadata
import com.alperbry.buildtrackerclient.model.OperatingSystemMetadata

interface BuildInformationReporter {

    fun report(
        extension: BuildTrackerExtension,
        cache: BuildInformationCache<AndroidBuildInfo>,
        timer: Timer
    )
}

class BuildInformationReporterImpl : BuildInformationReporter {

    override fun report(
        extension: BuildTrackerExtension,
        cache: BuildInformationCache<AndroidBuildInfo>,
        timer: Timer
    ) {

        val projectInfo = cache.projectInfo ?: return

        val firebaseConfig = extension.firebase

        val client = try {
            BuildTrackerClientProvider.client(
                email = firebaseConfig.email,
                password = firebaseConfig.password,
                apiKey = firebaseConfig.apiKey,
                databaseId = firebaseConfig.databaseId
            )
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return
        }

        val hardwareMetadata = HardwareMetadata(
            coreCount = cache.environmentData?.hardwareMetadata?.coreCount ?: 0,
            cpuModel = cache.environmentData?.hardwareMetadata?.cpuModelName ?: "",
            environmentIdentifier = cache.environmentData?.hardwareMetadata?.environmentIdentifier ?: "",
            physicalMemoryInMb = (cache.environmentData?.hardwareMetadata?.physicalMemoryInMb ?: 0L).toInt()
        )

        val osMetadata = OperatingSystemMetadata(
            name = cache.environmentData?.osMetadata?.name?.name ?: "",
            architecture = cache.environmentData?.osMetadata?.architecture ?: "",
            version = cache.environmentData?.osMetadata?.version ?: ""
        )

        val builds = cache.outputList.map {
            mapOf(
                "id" to it.id as Any,
                "moduleName" to it.moduleName as Any,
                "versionCode" to it.versionCode as Any,
                "flavorName" to it.flavorName as Any,
                "buildType" to it.buildType as Any
            )
        }

        println("***** ${client.newBuild(projectInfo.id, projectInfo.stateIdentifier, timer.duration(), hardwareMetadata, osMetadata, builds)}")
    }
}
