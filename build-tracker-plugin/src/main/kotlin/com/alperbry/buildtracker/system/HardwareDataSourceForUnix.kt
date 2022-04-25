package com.alperbry.buildtracker.system

import com.alperbry.buildtracker.util.commandline.CommandLineExecutor

class HardwareDataSourceForUnix(
    private val executor: CommandLineExecutor
) : HardwareDataSource {

    override fun cpuModel(): String {
        return executor.execute(
            "sysctl",
            "-n",
            "machdep.cpu.brand_string"
        ).trim()
    }

    override fun totalPhysicalMemoryInByte(): Long? {
        return executor.execute(
            "sysctl",
            "-n",
            "hw.memsize"
        ).trim().toLongOrNull()
    }

    override fun coreCount(): Int {
        return executor.execute(
            "sysctl",
            "-n",
            "hw.physicalcpu"
        ).trim().toInt()
    }

    override fun environmentIdentifier(): String {
        return executor.execute(
            "sysctl",
            "-n",
            "hw.model"
        ).trim()
    }
}
