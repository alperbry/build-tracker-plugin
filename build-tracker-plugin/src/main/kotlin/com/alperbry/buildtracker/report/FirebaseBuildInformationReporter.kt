package com.alperbry.buildtracker.report

import com.alperbry.buildtracker.data.GradleProjectBuildInfo
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtrackerclient.firebase.client.BuildTrackerClientProvider
import com.alperbry.buildtrackerclient.model.HardwareMetadata
import com.alperbry.buildtrackerclient.model.OperatingSystemMetadata

class FirebaseBuildInformationReporter(
    private val clientProvider: BuildTrackerClientProvider = BuildTrackerClientProvider
) : BuildInformationReporter {

    override fun report(
        extension: BuildTrackerExtension,
        buildInfo: GradleProjectBuildInfo
    ) {
        val firebaseConfig = extension.firebase

        val client = try {
            clientProvider.client(
                email = firebaseConfig.email,
                password = firebaseConfig.password,
                apiKey = firebaseConfig.apiKey,
                databaseId = firebaseConfig.databaseId
            )
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return
        }

        with(buildInfo) {
            client.newBuild(
                projectId = projectId,
                stateIdentifier = stateIdentifier,
                durationInMs = durationInMs,
                hardwareMetadata = HardwareMetadata(
                    coreCount = hardwareMetadata.coreCount,
                    physicalMemoryInMb = hardwareMetadata.physicalMemoryInMb.toInt(),
                    environmentIdentifier = hardwareMetadata.environmentIdentifier,
                    cpuModel = hardwareMetadata.cpuModelName
                ),
                osMetadata = OperatingSystemMetadata(
                    name = osMetadata.name.name,
                    architecture = osMetadata.architecture,
                    version = osMetadata.version
                ),
                outputInformation = outputs
            )
        }
    }
}
