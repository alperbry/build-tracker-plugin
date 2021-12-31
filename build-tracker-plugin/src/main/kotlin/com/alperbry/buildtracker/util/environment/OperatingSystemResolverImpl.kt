package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.OperatingSystem
import com.alperbry.buildtracker.data.OperatingSystemInformation
import com.alperbry.buildtracker.system.SystemRepository

class OperatingSystemResolverImpl(
    private val systemRepository: SystemRepository
) : OperatingSystemResolver {

    override fun operatingSystemInformation(): OperatingSystemInformation {
        return OperatingSystemInformation(
            name = systemRepository.osName.resolveOperatingSystem(),
            architecture = systemRepository.architecture,
            version = systemRepository.version
        )
    }

    private fun String.resolveOperatingSystem() = when {
        contains("mac") -> OperatingSystem.MAC_OS
        contains("win") -> OperatingSystem.WINDOWS
        contains("nux") -> OperatingSystem.LINUX
        else -> OperatingSystem.OTHER
    }
}
