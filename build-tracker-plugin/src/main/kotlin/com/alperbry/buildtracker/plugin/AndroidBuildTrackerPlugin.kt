package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildInfo
import com.alperbry.buildtracker.di.AndroidBuildDependencyProviderImpl
import com.alperbry.buildtracker.di.AndroidDependencyProvider
import com.alperbry.buildtracker.di.AndroidDependencyProviderImpl
import com.alperbry.buildtracker.di.CacheDependencyProvider
import com.alperbry.buildtracker.di.CacheDependencyProviderImpl
import com.alperbry.buildtracker.di.ProjectResolverDependencyProvider
import com.alperbry.buildtracker.di.ProjectResolverDependencyProviderImpl
import com.alperbry.buildtracker.di.VCSDependencyProviderImpl
import com.alperbry.buildtracker.task.AndroidOutputMetadataTask
import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolver
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidBuildTrackerPlugin : Plugin<Project> {

    private val resolverDependencyProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl()

    private val androidDependencyProvider: AndroidDependencyProvider = AndroidDependencyProviderImpl()

    private val cacheDependencyProvider: CacheDependencyProvider = CacheDependencyProviderImpl

    private val projectTypeResolver: AndroidProjectTypeResolver
        get() = resolverDependencyProvider.androidProjectTypeResolver()

    private val cache: BuildInformationCache<AndroidBuildInfo>
        get() = cacheDependencyProvider.androidBuildCache()

    override fun apply(project: Project) {

        val vcsDependencyProvider = VCSDependencyProviderImpl(project)

        androidDependencyProvider.extensionExtractor(
            projectTypeResolver.type(project)
        ).withExtensions(project) { androidExtensions ->
            val assembleTask = androidExtensions.variant.assembleTask

            val buildInfoTask = project.tasks.register(
                "androidBuildInformation${androidExtensions.variant.variantName.capitalize()}",
                AndroidOutputMetadataTask::class.java,
                AndroidBuildDependencyProviderImpl(project, vcsDependencyProvider),
                androidExtensions,
                cache
            )

            project.tasks.named(assembleTask).configure { assemble ->
                assemble.finalizedBy(buildInfoTask)
            }
        }
    }
}
