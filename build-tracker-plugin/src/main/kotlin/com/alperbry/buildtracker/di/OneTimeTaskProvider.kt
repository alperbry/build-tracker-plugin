package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtracker.task.OneTimeTaskManager

interface OneTimeTaskProvider {

    fun oneTimeTaskManager(): OneTimeTaskManager
}

class OneTimeTaskProviderImpl(
    private val projectInformationDependencyProvider: ProjectInformationDependencyProvider,
    private val buildTrackerExtension: BuildTrackerExtension,
    private val moduleExtensionExtractorProvider: ModuleExtensionExtractorProvider = ModuleExtensionExtractorProviderImpl(),
    private val resolverProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl(),
    private val reporterDependencyProvider: ReporterDependencyProvider = ReporterDependencyProviderImpl(),
    private val cacheDependencyProvider: CacheDependencyProvider = CacheDependencyProviderImpl,
    private val timerDependencyProvider: TimerDependencyProvider = TimerDependencyProviderImpl
) : OneTimeTaskProvider {

    override fun oneTimeTaskManager(): OneTimeTaskManager {
        return OneTimeTaskManager(
            moduleExtensionExtractorProvider,
            projectInformationDependencyProvider.projectInformationResolver(),
            resolverProvider.projectTypeResolver(),
            reporterDependencyProvider.reporter(),
            buildTrackerExtension,
            cacheDependencyProvider.androidBuildCache(),
            timerDependencyProvider.timer
        )
    }
}
