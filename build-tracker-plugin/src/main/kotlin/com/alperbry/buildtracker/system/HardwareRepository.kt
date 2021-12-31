package com.alperbry.buildtracker.system

interface HardwareRepository {

    fun cpuBrand(): String

    fun totalPhysicalMemory(): Long?

    fun coreCount(): Int

    fun environmentIdentifier(): String
}
