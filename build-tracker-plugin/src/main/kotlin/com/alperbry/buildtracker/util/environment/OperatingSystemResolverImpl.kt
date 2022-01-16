package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.OperatingSystem
import com.alperbry.buildtracker.data.OperatingSystemInformation
import com.alperbry.buildtracker.system.SystemDataSource

class OperatingSystemResolverImpl(
    private val source: SystemDataSource
) : OperatingSystemResolver {

    override fun operatingSystemInformation(): OperatingSystemInformation {
        return OperatingSystemInformation(
            name = source.osName.resolveOperatingSystem(),
            architecture = source.architecture,
            version = source.version
        )
    }

    private fun String.resolveOperatingSystem() = when {
        contains("mac") -> OperatingSystem.MAC_OS
        contains("win") -> OperatingSystem.WINDOWS
        contains("nux") -> OperatingSystem.LINUX
        else -> OperatingSystem.OTHER
    }
}
