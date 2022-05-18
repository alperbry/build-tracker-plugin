package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.data.OperatingSystem
import com.alperbry.buildtracker.system.HardwareDataSourceForUnix
import com.alperbry.buildtracker.system.HardwareDataSourceForWindows
import com.alperbry.buildtracker.system.SystemDataSourceImpl
import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import com.alperbry.buildtracker.util.environment.HardwareResolver
import com.alperbry.buildtracker.util.environment.HardwareResolverImpl
import com.alperbry.buildtracker.util.environment.OperatingSystemResolver
import com.alperbry.buildtracker.util.environment.OperatingSystemResolverImpl
import org.gradle.api.Project

interface EnvironmentInformationDependencyProvider {

    val operatingSystemResolver: OperatingSystemResolver

    val hardwareResolver: HardwareResolver
}

class EnvironmentInformationDependencyProviderImpl(
    private val project: Project
) : EnvironmentInformationDependencyProvider {

    private val commandLineExecutor by lazy {
        CommandLineExecutorImpl(project)
    }

    override val operatingSystemResolver = OperatingSystemResolverImpl(SystemDataSourceImpl())

    override val hardwareResolver: HardwareResolver
        get() {
            return if (operatingSystemResolver.operatingSystemInformation().name == OperatingSystem.WINDOWS) {
                HardwareResolverImpl(HardwareDataSourceForWindows())
            } else {
                HardwareResolverImpl(HardwareDataSourceForUnix(commandLineExecutor))
            }
        }
}
