package com.alperbry.buildtracker.report

import com.alperbry.buildtracker.data.GradleProjectBuildInfo
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtracker.data.output.BuildInformationReportDTO
import com.alperbry.buildtracker.data.output.HardwareMetadataDTO
import com.alperbry.buildtracker.data.output.OSMetadataDTO
import com.alperbry.buildtracker.report.client.LocalStorageBuildInfoClient
import com.alperbry.buildtracker.util.timestamp.TimestampGenerator

class LocalBuildInformationReporter(
    private val client: LocalStorageBuildInfoClient,
    private val timestampGenerator: TimestampGenerator
) : BuildInformationReporter {

    override fun report(
        extension: BuildTrackerExtension,
        buildInfo: GradleProjectBuildInfo
    ) {
        with(buildInfo) {
            client.newBuild(
                BuildInformationReportDTO(
                    projectId = projectId,
                    stateIdentifier = stateIdentifier,
                    durationInMs = durationInMs,
                    hardware = HardwareMetadataDTO(
                        coreCount = hardwareMetadata.coreCount,
                        cpuModel = hardwareMetadata.cpuModelName,
                        environmentIdentifier = hardwareMetadata.environmentIdentifier,
                        physicalMemoryInMb = hardwareMetadata.physicalMemoryInMb.toInt()
                    ),
                    operatingSystem = OSMetadataDTO(
                        name = osMetadata.name.name,
                        architecture = osMetadata.architecture,
                        version = osMetadata.version
                    ),
                    outputs = outputs.map {
                        it.mapValues {
                            it.value.toString()
                        }
                    },
                    timestamp = timestampGenerator.now()
                ),
                directory = null // todo make it configurable
            )
        }
    }
}
