package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.HardwareInformation
import com.alperbry.buildtracker.system.HardwareRepository

class HardwareResolverImpl(
    private val repository: HardwareRepository
) : HardwareResolver {

    override fun hardwareInfo(): HardwareInformation {
        return HardwareInformation(
            cpuModelName = repository.cpuBrand(),
            physicalMemoryInMb = repository.totalPhysicalMemory()?.div(MODULO_FROM_BYTE_TO_MB) ?: -1,
            environmentIdentifier = repository.environmentIdentifier(),
            coreCount = repository.coreCount()
        )
    }
}

private const val MODULO_FROM_BYTE_TO_MB = 1024 * 1024
