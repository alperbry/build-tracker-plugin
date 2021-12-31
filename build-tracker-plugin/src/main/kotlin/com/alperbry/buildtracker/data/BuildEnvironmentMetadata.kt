package com.alperbry.buildtracker.data

data class BuildEnvironmentMetadata(
    val osMetadata: OperatingSystemInformation,
    val hardwareMetadata: HardwareInformation
    )

data class OperatingSystemInformation(
    val name: OperatingSystem,
    val architecture: String,
    val version: String
)

data class HardwareInformation(
    val cpuModelName: String,
    val totalMemoryInMb: Long,
    val environmentIdentifier: String,
    val coreCount: Int
)
