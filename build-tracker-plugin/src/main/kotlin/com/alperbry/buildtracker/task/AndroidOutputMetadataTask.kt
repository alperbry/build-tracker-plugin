package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.di.AndroidBuildDependencyProvider
import com.alperbry.buildtracker.util.android.apk.ApkResolver
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class AndroidOutputMetadataTask @Inject constructor(
    private val provider: AndroidBuildDependencyProvider,
    private val extension: BuildTrackerAndroidExtensions
) : DefaultTask() {

    private val apkResolver: ApkResolver
        get() = provider.apkResolver()

    @TaskAction
    fun execute() {

        println(apkResolver.buildInfo(extension))
    }
}
