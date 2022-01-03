package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.system.HardwareRepositoryForUnix
import com.alperbry.buildtracker.system.SystemRepositoryImpl
import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import com.alperbry.buildtracker.util.environment.HardwareResolver
import com.alperbry.buildtracker.util.environment.HardwareResolverImpl
import com.alperbry.buildtracker.util.environment.OperatingSystemResolver
import com.alperbry.buildtracker.util.environment.OperatingSystemResolverImpl
import org.gradle.api.Project

interface EnvironmentInformationDependencyProvider {

    fun operatingSystemResolver(): OperatingSystemResolver

    fun hardwareResolver(): HardwareResolver
}

class EnvironmentInformationDependencyProviderImpl(
    private val project: Project
) : EnvironmentInformationDependencyProvider {

    override fun operatingSystemResolver() = OperatingSystemResolverImpl(SystemRepositoryImpl())

    override fun hardwareResolver() = HardwareResolverImpl(HardwareRepositoryForUnix(CommandLineExecutorImpl(project)))
}
