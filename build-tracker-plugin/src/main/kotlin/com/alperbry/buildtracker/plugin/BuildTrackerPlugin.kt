package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.data.GradleProjectType
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtracker.di.ProjectResolverDependencyProvider
import com.alperbry.buildtracker.di.ProjectResolverDependencyProviderImpl
import com.alperbry.buildtracker.di.TimerDependencyProvider
import com.alperbry.buildtracker.di.TimerDependencyProviderImpl
import com.alperbry.buildtracker.util.extension.afterEachSubProjectEvaluated
import com.alperbry.buildtracker.util.project.ProjectTypeResolver
import org.gradle.api.Plugin
import org.gradle.api.Project

open class BuildTrackerPlugin : Plugin<Project> {

    private val resolverProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl()

    private val timerProvider: TimerDependencyProvider = TimerDependencyProviderImpl

    private val projectTypeResolver: ProjectTypeResolver
        get() = resolverProvider.projectTypeResolver()

    init {
        val timer = timerProvider.timer
        timer.reset()
    }

    override fun apply(project: Project) {

        project.extensions.create(
            BuildTrackerExtension.EXTENSION_NAME,
            BuildTrackerExtension::class.java
        )

        project.afterEvaluate {
            it.apply { configurationAction ->
                configurationAction.plugin(RootProjectTrackerPlugin::class.java)
            }
        }

        project.afterEachSubProjectEvaluated { evaluatedProject ->
            val trackerPluginClazz = when (projectTypeResolver.type(evaluatedProject)) {
                is GradleProjectType.Android -> AndroidBuildTrackerPlugin::class.java
                else -> return@afterEachSubProjectEvaluated//throw IllegalStateException("Only Android projects are supported yet.")
            }

            evaluatedProject.apply { configurationAction ->
                configurationAction.plugin(trackerPluginClazz)
            }
        }
    }
}
