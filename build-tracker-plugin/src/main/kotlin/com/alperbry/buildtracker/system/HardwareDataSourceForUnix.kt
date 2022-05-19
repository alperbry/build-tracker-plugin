package com.alperbry.buildtracker.system

import com.alperbry.buildtracker.util.commandline.CommandLineExecutor
import com.alperbry.buildtracker.util.commandline.safeExecute
import java.lang.NumberFormatException

class HardwareDataSourceForUnix(
    private val executor: CommandLineExecutor
) : HardwareDataSource {

    override fun cpuModel(): String {
        return executor.safeExecute(
            "sysctl",
            "-n",
            "machdep.cpu.brand_string"
        ).trim()
    }

    override fun totalPhysicalMemoryInByte(): Long? {
        return executor.safeExecute(
            "sysctl",
            "-n",
            "hw.memsize"
        ).trim().toLongOrNull()
    }

    override fun coreCount(): Int {
        return try {
            executor.safeExecute(
                "sysctl",
                "-n",
                "hw.physicalcpu"
            ).trim().toInt()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            0
        }
    }

    override fun environmentIdentifier(): String {
        return executor.safeExecute(
            "sysctl",
            "-n",
            "hw.model"
        ).trim()
    }
}
