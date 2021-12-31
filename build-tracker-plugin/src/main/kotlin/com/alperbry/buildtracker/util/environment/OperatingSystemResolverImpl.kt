package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.OperatingSystem
import com.alperbry.buildtracker.system.SystemRepository

class OperatingSystemResolverImpl(
    private val systemRepository: SystemRepository
) : OperatingSystemResolver {

    override fun operatingSystem(): OperatingSystem {
        return when {
            systemRepository.osName.contains("mac") -> OperatingSystem.MAC_OS
            systemRepository.osName.contains("win") -> OperatingSystem.WINDOWS
            systemRepository.osName.contains("nux") -> OperatingSystem.LINUX
            else -> OperatingSystem.OTHER
        }
    }
}
