package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.OperatingSystem

interface OperatingSystemResolver {

    fun operatingSystem(): OperatingSystem
}
