package com.alperbry.buildtracker.data

data class GradleProjectBuildInfo(
    val projectId: String,
    val stateIdentifier: String,
    val durationInMs: Long,
    val hardwareMetadata: HardwareInformation,
    val osMetadata: OperatingSystemInformation,
    val outputs: List<Map<String, Any>>
)
