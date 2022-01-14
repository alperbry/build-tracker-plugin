package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.di.AndroidBuildDependencyProvider
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class AndroidOutputMetadataTask @Inject constructor(
    private val provider: AndroidBuildDependencyProvider,
    private val extension: BuildTrackerAndroidExtensions,
    private val cache: BuildInformationCache<AndroidBuildMetadata>
) : DefaultTask() {

    private val buildResolver: AndroidBuildResolver
        get() = provider.buildResolver(extension.projectType)

    @TaskAction
    fun execute() {
        cache.outputList.add(
            buildResolver.buildInfo(extension)
        )
    }
}
