package com.alperbry.buildtracker.report

import com.alperbry.buildtracker.data.GradleProjectBuildInfo
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension

class CompositeBuildInformationReporter(
    private val reporters: List<BuildInformationReporter>,
) : BuildInformationReporter {

    override fun report(
        extension: BuildTrackerExtension,
        buildInfo: GradleProjectBuildInfo
    ) {
        reporters.forEach { reporter ->
            reporter.report(extension, buildInfo)
        }
    }
}
