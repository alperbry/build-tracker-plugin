package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.report.BuildInformationReporter
import com.alperbry.buildtracker.report.CompositeBuildInformationReporter
import com.alperbry.buildtracker.report.FirebaseBuildInformationReporter
import com.alperbry.buildtracker.report.LocalBuildInformationReporter
import com.alperbry.buildtracker.report.client.LocalStorageBuildInfoClientImpl

interface ReporterDependencyProvider {

    fun reporter(): BuildInformationReporter
}

class ReporterDependencyProviderImpl : ReporterDependencyProvider {

    private val reporter by lazy {
        CompositeBuildInformationReporter(
            listOf(
                LocalBuildInformationReporter(LocalStorageBuildInfoClientImpl()),
                FirebaseBuildInformationReporter()
            )
        )
    }

    override fun reporter(): BuildInformationReporter {
        return reporter
    }
}
