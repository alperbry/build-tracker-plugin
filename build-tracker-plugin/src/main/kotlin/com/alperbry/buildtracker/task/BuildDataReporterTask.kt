package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BuildDataReporterTask @Inject constructor(
    private val cache: BuildInformationCache<AndroidBuildMetadata>
) : DefaultTask() {

    @TaskAction
    fun execute() {
        println("reporting task, cache: ${cache.durationInMs}ms ${cache.environmentData} ${cache.outputList}")
    }
}
