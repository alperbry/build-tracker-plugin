package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProviderImpl
import com.alperbry.buildtracker.di.ModuleExtensionExtractorProvider
import com.alperbry.buildtracker.report.BuildInformationReporter
import com.alperbry.buildtracker.util.extension.extension
import com.alperbry.buildtracker.util.gradle.SimpleBuildListener
import com.alperbry.buildtracker.util.project.ProjectInformationResolver
import com.alperbry.buildtracker.util.project.ProjectTypeResolver
import com.alperbry.buildtracker.util.timer.Timer
import org.gradle.BuildResult
import org.gradle.api.invocation.Gradle

class OneTimeTaskManager(
    private val moduleExtensionExtractorProvider: ModuleExtensionExtractorProvider,
    private val projectInformationResolver: ProjectInformationResolver,
    private val projectTypeResolver: ProjectTypeResolver,
    private val reporter: BuildInformationReporter,
    private val extension: BuildTrackerExtension,
    private val cache: BuildInformationCache<AndroidBuildInfo>, // todo should not be android dependent
    private val timer: Timer,
) : SimpleBuildListener() {

    override fun projectsEvaluated(gradle: Gradle) {

        val buildEnvironmentTask = gradle.rootProject.tasks.register(
            "buildEnvironmentInformation",
            BuildEnvironmentMetadataTask::class.java,
            EnvironmentInformationDependencyProviderImpl(gradle.rootProject),
            cache
        )

        val projectMetadataTask = gradle.rootProject.tasks.register(
            "projectMetadata",
            ProjectMetadataTask::class.java,
            projectInformationResolver,
            extension<BuildTrackerExtension>(gradle.rootProject),
            cache
        )

        gradle.rootProject.childProjects.forEach { (_, child) ->
            // todo recursive child check
            if (child.childProjects.isNotEmpty()) return@forEach

            moduleExtensionExtractorProvider.extensionExtractor(
                projectTypeResolver.type(child)
            ).withExtensions(child) { extension ->
                child.tasks.named(extension.assembleTask).configure { assemble ->
                    assemble.dependsOn(buildEnvironmentTask, projectMetadataTask)
                }
            }
        }
    }

    override fun buildFinished(buildResult: BuildResult) {
        reporter.report(extension, cache, timer)
        cache.dispose()
    }
}
