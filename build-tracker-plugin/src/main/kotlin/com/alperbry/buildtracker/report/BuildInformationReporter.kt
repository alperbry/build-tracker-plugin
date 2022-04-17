package com.alperbry.buildtracker.report

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.util.timer.Timer

interface BuildInformationReporter {

    fun report(
        cache: BuildInformationCache<AndroidBuildInfo>,
        timer: Timer
    )
}

class BuildInformationReporterImpl : BuildInformationReporter {

    override fun report(
        cache: BuildInformationCache<AndroidBuildInfo>,
        timer: Timer
    ) {
        println("reporting task, cache: ${timer.duration()}ms \n${cache.environmentData} \n${cache.outputList}")
    }
}
