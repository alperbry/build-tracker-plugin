package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.report.BuildInformationReporter
import com.alperbry.buildtracker.report.BuildInformationReporterImpl

interface ReporterDependencyProvider {

    fun reporter(): BuildInformationReporter
}

class ReporterDependencyProviderImpl : ReporterDependencyProvider {

    private val reporter by lazy {
        BuildInformationReporterImpl()
    }

    override fun reporter(): BuildInformationReporter {
        return reporter
    }
}
