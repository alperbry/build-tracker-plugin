package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.util.timer.Timer
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BuildDataReporterTask @Inject constructor(
    private val cache: BuildInformationCache<AndroidBuildMetadata>,
    private val timer: Timer
) : DefaultTask() {

    @TaskAction
    fun execute() {
        println("reporting task, cache: ${timer.duration()}ms \n${cache.environmentData} \n${cache.outputList} \n${cache.vcsData}")
    }
}
