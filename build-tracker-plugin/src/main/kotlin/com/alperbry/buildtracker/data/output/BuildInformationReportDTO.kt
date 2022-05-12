package com.alperbry.buildtracker.data.output

import kotlinx.serialization.Serializable

@Serializable
data class BuildInformationReportDTO(
    val projectId: String,
    val stateIdentifier: String,
    val timestamp: String,
    val durationInMs: Long,
    val hardware: HardwareMetadataDTO,
    val operatingSystem: OSMetadataDTO,
    val outputs: List<Map<String, String>>
)
