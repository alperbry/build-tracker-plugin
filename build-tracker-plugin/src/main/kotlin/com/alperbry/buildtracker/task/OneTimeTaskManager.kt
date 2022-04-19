package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProviderImpl
import com.alperbry.buildtracker.di.ExtensionExtractorProvider
import com.alperbry.buildtracker.report.BuildInformationReporter
import com.alperbry.buildtracker.util.gradle.SimpleBuildListener
import com.alperbry.buildtracker.util.project.ProjectTypeResolver
import com.alperbry.buildtracker.util.timer.Timer
import org.gradle.BuildResult
import org.gradle.api.invocation.Gradle

class OneTimeTaskManager(
    private val extensionExtractorProvider: ExtensionExtractorProvider,
    private val projectTypeResolver: ProjectTypeResolver,
    private val reporter: BuildInformationReporter,
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

        gradle.rootProject.childProjects.forEach { (_, child) ->
            // todo recursive child check
            if (child.childProjects.isNotEmpty()) return@forEach

            extensionExtractorProvider.extensionExtractor(
                projectTypeResolver.type(child)
            ).withExtensions(child) { extension ->
                child.tasks.named(extension.assembleTask).configure { assemble ->
                    assemble.dependsOn(buildEnvironmentTask)
                }
            }
        }
    }

    override fun buildFinished(buildResult: BuildResult) {
        reporter.report(cache, timer)
        cache.dispose()
    }
}
