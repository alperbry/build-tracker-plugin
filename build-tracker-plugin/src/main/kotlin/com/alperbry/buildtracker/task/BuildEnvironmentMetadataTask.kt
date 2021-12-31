package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProvider
import com.alperbry.buildtracker.util.environment.OperatingSystemResolver
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

open class BuildEnvironmentMetadataTask @Inject constructor(
    private val provider: EnvironmentInformationDependencyProvider
) : DefaultTask() {

    private val resolver: OperatingSystemResolver
        get() = provider.operatingSystemResolver()

    @TaskAction
    fun execute() {
        val operatingSystemInfo = resolver.operatingSystemInformation()
        println(operatingSystemInfo)
    }
}
