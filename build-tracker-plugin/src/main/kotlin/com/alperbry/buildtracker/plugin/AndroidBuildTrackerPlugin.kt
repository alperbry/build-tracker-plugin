package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.di.AndroidBuildDependencyProviderImpl
import com.alperbry.buildtracker.di.AndroidDependencyProvider
import com.alperbry.buildtracker.di.AndroidDependencyProviderImpl
import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProviderImpl
import com.alperbry.buildtracker.di.ProjectResolverDependencyProvider
import com.alperbry.buildtracker.di.ProjectResolverDependencyProviderImpl
import com.alperbry.buildtracker.task.AndroidOutputMetadataTask
import com.alperbry.buildtracker.task.BuildEnvironmentMetadataTask
import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolver
import com.alperbry.buildtracker.util.taskexecution.TimeTrackerExecutionListener
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidBuildTrackerPlugin : Plugin<Project> {

    private val resolverDependencyProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl()

    private val androidDependencyProvider: AndroidDependencyProvider = AndroidDependencyProviderImpl()

    private val projectTypeResolver: AndroidProjectTypeResolver
        get() = resolverDependencyProvider.androidProjectTypeResolver()

    override fun apply(project: Project) {

        val timeTrackerExecutionListener = TimeTrackerExecutionListener()

        project.gradle.addBuildListener(timeTrackerExecutionListener)
        //project.gradle.taskGraph.addTaskExecutionListener(timeTrackerExecutionListener)

        androidDependencyProvider.buildTrackerHelper(
            projectTypeResolver.type(project)
        ).withExtensions(project) { androidExtensions ->

            val assembleTask = androidExtensions.variant.assembleTask

            project.tasks.register(
                "operatingSystemTask${androidExtensions.variant.variantName.capitalize()}",
                BuildEnvironmentMetadataTask::class.java,
                EnvironmentInformationDependencyProviderImpl(project)
            )

            val androidMetadataTask = project.tasks.register(
                "androidOutputMetadataTask${androidExtensions.variant.variantName.capitalize()}",
                AndroidOutputMetadataTask::class.java,
                AndroidBuildDependencyProviderImpl(project),
                androidExtensions
            )

            project.tasks.named(assembleTask).configure {
                it.finalizedBy(androidMetadataTask)
            }
        }

        //project.tasks.findByName("clean")//?
    }
}
