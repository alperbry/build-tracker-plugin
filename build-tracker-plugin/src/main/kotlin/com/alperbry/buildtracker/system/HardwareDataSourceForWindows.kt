package com.alperbry.buildtracker.system

// TODO: Dummy hardware data source, to be implemented for windows machines.
class HardwareDataSourceForWindows : HardwareDataSource {

    override fun cpuModel(): String {
        return ""
    }

    override fun totalPhysicalMemoryInByte(): Long {
        return -1L
    }

    override fun coreCount(): Int {
        return -1
    }

    override fun environmentIdentifier(): String {
        return ""
    }
}
