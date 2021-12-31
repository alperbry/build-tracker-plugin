package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.data.BuildEnvironmentMetadata
import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProvider
import com.alperbry.buildtracker.util.environment.HardwareResolver
import com.alperbry.buildtracker.util.environment.OperatingSystemResolver
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

open class BuildEnvironmentMetadataTask @Inject constructor(
    private val provider: EnvironmentInformationDependencyProvider
) : DefaultTask() {

    private val osResolver: OperatingSystemResolver
        get() = provider.operatingSystemResolver()

    private val hardwareResolver: HardwareResolver
        get() = provider.hardwareResolver()

    @TaskAction
    fun execute() {
        val operatingSystemInfo = osResolver.operatingSystemInformation()

        val hardwareInfo = hardwareResolver.hardwareInfo()

        println(BuildEnvironmentMetadata(operatingSystemInfo, hardwareInfo))
    }
}
