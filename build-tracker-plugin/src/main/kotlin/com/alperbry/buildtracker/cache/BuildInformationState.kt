package com.alperbry.buildtracker.cache

import com.alperbry.buildtracker.data.GradleProjectBuildInfo
import com.alperbry.buildtracker.util.timer.Timer

interface BuildInformationState {

    fun valid(): Boolean

    fun snapshot(timer: Timer): GradleProjectBuildInfo?
}
