package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.di.AndroidBuildDependencyProvider
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import com.alperbry.buildtracker.util.git.GitResolverImpl
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class AndroidOutputMetadataTask @Inject constructor(
    private val provider: AndroidBuildDependencyProvider,
    private val extension: BuildTrackerAndroidExtensions
) : DefaultTask() {

    private val buildResolver: AndroidBuildResolver
        get() = provider.buildResolver(extension.projectType)

    @TaskAction
    fun execute() {

        println(buildResolver.buildInfo(extension))

        println(GitResolverImpl(CommandLineExecutorImpl(project)).revision())
    }
}
