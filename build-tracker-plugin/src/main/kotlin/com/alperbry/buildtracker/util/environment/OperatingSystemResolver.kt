package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.OperatingSystemInformation

interface OperatingSystemResolver {

    fun operatingSystemInformation(): OperatingSystemInformation
}
