package com.alperbry.buildtracker.data.output

import kotlinx.serialization.Serializable

@Serializable
data class HardwareMetadataDTO(
    val coreCount: Int,
    val cpuModel: String,
    val environmentIdentifier: String,
    val physicalMemoryInMb: Int
)
