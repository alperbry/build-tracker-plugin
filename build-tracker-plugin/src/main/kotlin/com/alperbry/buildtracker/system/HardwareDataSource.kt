package com.alperbry.buildtracker.system

/**
 * Data source for Hardware related information
 */
interface HardwareDataSource {

    /**
     * Human-readable model name of the CPU.
     */
    fun cpuModel(): String

    /**
     * Total physical memory available on the environment in Bytes.
     */
    fun totalPhysicalMemoryInByte(): Long?

    /**
     * Physical core count of the CPU.
     */
    fun coreCount(): Int

    /**
     * An identifier for the environment, model of the computer would be a good
     * candidate for personal computers.
     */
    fun environmentIdentifier(): String
}
