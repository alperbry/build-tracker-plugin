package com.alperbry.buildtracker.system

import com.alperbry.buildtracker.util.commandline.CommandLineExecutor

class HardwareRepositoryForUnix(
    private val executor: CommandLineExecutor
) : HardwareRepository {

    override fun cpuBrand(): String {
        return executor.execute(
            "sysctl",
            "-n",
            "machdep.cpu.brand_string"
        )
    }

    override fun totalPhysicalMemory(): Long? {
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
        )
    }
}
