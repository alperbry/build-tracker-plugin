package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.HardwareInformation

interface HardwareResolver {

    fun hardwareInfo(): HardwareInformation
}
