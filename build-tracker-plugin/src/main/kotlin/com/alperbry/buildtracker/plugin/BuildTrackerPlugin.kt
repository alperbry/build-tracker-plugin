package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.data.GradleProjectType
import com.alperbry.buildtracker.di.ProjectResolverDependencyProvider
import com.alperbry.buildtracker.di.ProjectResolverDependencyProviderImpl
import com.alperbry.buildtracker.di.TimerDependencyProvider
import com.alperbry.buildtracker.di.TimerDependencyProviderImpl
import com.alperbry.buildtracker.util.project.ProjectTypeResolver
import org.gradle.api.Plugin
import org.gradle.api.Project

open class BuildTrackerPlugin : Plugin<Project> {

    private val resolverProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl()

    private val timerProvider: TimerDependencyProvider = TimerDependencyProviderImpl

    private val projectTypeResolver: ProjectTypeResolver
        get() = resolverProvider.projectTypeResolver()

    override fun apply(project: Project) {

        val timer = timerProvider.timer
        timer.reset()

        project.afterEvaluate {
            val trackerPluginClazz = when (projectTypeResolver.type(project)) {
                GradleProjectType.ANDROID -> AndroidBuildTrackerPlugin::class.java
                else -> throw IllegalStateException("Only Android projects are supported yet.")
            }

            project.apply { configurationAction ->
                configurationAction.plugin(trackerPluginClazz)
            }
        }
    }
}
