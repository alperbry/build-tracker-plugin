package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.di.AndroidBuildDependencyProviderImpl
import com.alperbry.buildtracker.di.AndroidDependencyProvider
import com.alperbry.buildtracker.di.AndroidDependencyProviderImpl
import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProviderImpl
import com.alperbry.buildtracker.di.ProjectResolverDependencyProvider
import com.alperbry.buildtracker.di.ProjectResolverDependencyProviderImpl
import com.alperbry.buildtracker.di.VCSDependencyProviderImpl
import com.alperbry.buildtracker.task.AndroidOutputMetadataTask
import com.alperbry.buildtracker.task.BuildEnvironmentMetadataTask
import com.alperbry.buildtracker.task.BuildDataReporterTask
import com.alperbry.buildtracker.task.VCSMetadataTask
import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolver
import com.alperbry.buildtracker.util.taskexecution.TimeTrackerExecutionListener
import com.alperbry.buildtracker.util.timer.TimerImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidBuildTrackerPlugin : Plugin<Project> {

    private val resolverDependencyProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl()

    private val androidDependencyProvider: AndroidDependencyProvider = AndroidDependencyProviderImpl()

    private val projectTypeResolver: AndroidProjectTypeResolver
        get() = resolverDependencyProvider.androidProjectTypeResolver()

    override fun apply(project: Project) {

        val osTask = project.tasks.register(
            "operatingSystemTask",
            BuildEnvironmentMetadataTask::class.java,
            EnvironmentInformationDependencyProviderImpl(project)
        )

        val vcsTask = project.tasks.register(
            "vcsMetadataTask",
            VCSMetadataTask::class.java,
            VCSDependencyProviderImpl(project)
        )

        val reporterTask = project.tasks.register(
            "finalReporterTask",
            BuildDataReporterTask::class.java
        )

        val timeTracker = TimeTrackerExecutionListener(TimerImpl(), reporterTask.name)
        project.gradle.addListener(timeTracker)

        androidDependencyProvider.buildTrackerHelper(
            projectTypeResolver.type(project)
        ).withExtensions(project) { androidExtensions ->

            val assembleTask = androidExtensions.variant.assembleTask

            val androidMetadataTask = project.tasks.register(
                "androidOutputMetadataTask${androidExtensions.variant.variantName.capitalize()}",
                AndroidOutputMetadataTask::class.java,
                AndroidBuildDependencyProviderImpl(project),
                androidExtensions
            )

            project.tasks.named(assembleTask).configure { assemble ->
                assemble.finalizedBy(androidMetadataTask)
            }

            androidMetadataTask.configure {
                it.dependsOn(osTask)
                it.dependsOn(vcsTask)
                it.finalizedBy(reporterTask)
            }

            reporterTask.configure {
                it.mustRunAfter(assembleTask)
            }
        }
    }
}
