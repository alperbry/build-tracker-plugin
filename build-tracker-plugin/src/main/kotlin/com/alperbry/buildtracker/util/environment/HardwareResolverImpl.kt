package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.HardwareInformation
import com.alperbry.buildtracker.system.HardwareDataSource

class HardwareResolverImpl(
    private val source: HardwareDataSource
) : HardwareResolver {

    override fun hardwareInfo(): HardwareInformation {
        return HardwareInformation(
            cpuModelName = source.cpuBrand(),
            physicalMemoryInMb = source.totalPhysicalMemory()?.div(MODULO_FROM_BYTE_TO_MB) ?: -1,
            environmentIdentifier = source.environmentIdentifier(),
            coreCount = source.coreCount()
        )
    }
}

private const val MODULO_FROM_BYTE_TO_MB = 1024 * 1024
