package com.alperbry.buildtracker.system

interface HardwareDataSource {

    fun cpuBrand(): String

    fun totalPhysicalMemory(): Long?

    fun coreCount(): Int

    fun environmentIdentifier(): String
}
