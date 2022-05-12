package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.report.BuildInformationReporter
import com.alperbry.buildtracker.report.CompositeBuildInformationReporter
import com.alperbry.buildtracker.report.FirebaseBuildInformationReporter
import com.alperbry.buildtracker.report.LocalBuildInformationReporter
import com.alperbry.buildtracker.report.client.LocalStorageBuildInfoClientImpl
import com.alperbry.buildtracker.util.timestamp.TimestampGeneratorImpl
import org.gradle.api.Project

interface ReporterDependencyProvider {

    fun reporter(): BuildInformationReporter
}

class ReporterDependencyProviderImpl(
    private val project: Project
) : ReporterDependencyProvider {

    private val reporter by lazy {
        CompositeBuildInformationReporter(
            listOf(
                LocalBuildInformationReporter(
                    LocalStorageBuildInfoClientImpl(project),
                    TimestampGeneratorImpl()
                ),
                FirebaseBuildInformationReporter()
            )
        )
    }

    override fun reporter(): BuildInformationReporter {
        return reporter
    }
}
