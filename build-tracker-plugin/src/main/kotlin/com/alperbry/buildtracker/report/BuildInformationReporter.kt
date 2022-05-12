package com.alperbry.buildtracker.report

import com.alperbry.buildtracker.data.GradleProjectBuildInfo
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension

interface BuildInformationReporter {

    fun report(
        extension: BuildTrackerExtension,
        buildInfo: GradleProjectBuildInfo
    )
}
