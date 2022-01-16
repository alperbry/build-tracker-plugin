package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata
import com.alperbry.buildtracker.di.AndroidBuildDependencyProviderImpl
import com.alperbry.buildtracker.di.AndroidDependencyProvider
import com.alperbry.buildtracker.di.AndroidDependencyProviderImpl
import com.alperbry.buildtracker.di.CacheDependencyProvider
import com.alperbry.buildtracker.di.CacheDependencyProviderImpl
import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProviderImpl
import com.alperbry.buildtracker.di.ProjectResolverDependencyProvider
import com.alperbry.buildtracker.di.ProjectResolverDependencyProviderImpl
import com.alperbry.buildtracker.di.VCSDependencyProviderImpl
import com.alperbry.buildtracker.task.AndroidOutputMetadataTask
import com.alperbry.buildtracker.task.BuildEnvironmentMetadataTask
import com.alperbry.buildtracker.task.BuildDataReporterTask
import com.alperbry.buildtracker.task.VCSMetadataTask
import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolver
import com.alperbry.buildtracker.util.taskexecution.TimeTracker
import com.alperbry.buildtracker.util.timer.TimerImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidBuildTrackerPlugin : Plugin<Project> {

    private val resolverDependencyProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl()

    private val androidDependencyProvider: AndroidDependencyProvider = AndroidDependencyProviderImpl()

    private val cacheDependencyProvider: CacheDependencyProvider = CacheDependencyProviderImpl()

    private val projectTypeResolver: AndroidProjectTypeResolver
        get() = resolverDependencyProvider.androidProjectTypeResolver()

    private val cache: BuildInformationCache<AndroidBuildMetadata>
        get() = cacheDependencyProvider.androidBuildCache()

    override fun apply(project: Project) {

        val vcsDependencyProvider = VCSDependencyProviderImpl(project)

        val osTask = project.tasks.register(
            "operatingSystemTask",
            BuildEnvironmentMetadataTask::class.java,
            EnvironmentInformationDependencyProviderImpl(project),
            cache
        )

        val vcsTask = project.tasks.register(
            "vcsMetadataTask",
            VCSMetadataTask::class.java,
            vcsDependencyProvider,
            cache
        )

        val reporterTask = project.tasks.register(
            "finalReporterTask",
            BuildDataReporterTask::class.java,
            cache
        )

        val timeTracker = TimeTracker(TimerImpl(), reporterTask.name, cache)
        project.gradle.addListener(timeTracker)

        androidDependencyProvider.buildTrackerHelper(
            projectTypeResolver.type(project)
        ).withExtensions(project) { androidExtensions ->

            val assembleTask = androidExtensions.variant.assembleTask

            val androidMetadataTask = project.tasks.register(
                "androidOutputMetadataTask${androidExtensions.variant.variantName.capitalize()}",
                AndroidOutputMetadataTask::class.java,
                AndroidBuildDependencyProviderImpl(project, vcsDependencyProvider),
                androidExtensions,
                cache
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
