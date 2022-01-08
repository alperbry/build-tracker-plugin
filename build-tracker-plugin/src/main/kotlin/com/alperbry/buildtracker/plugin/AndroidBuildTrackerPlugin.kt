package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.alperbry.buildtracker.di.AndroidDependencyProvider
import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProviderImpl
import com.alperbry.buildtracker.di.ProjectResolverDependencyProvider
import com.alperbry.buildtracker.di.ProjectResolverDependencyProviderImpl
import com.alperbry.buildtracker.task.BuildEnvironmentMetadataTask
import com.alperbry.buildtracker.util.android.AndroidExtensionMapper
import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolver
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidBuildTrackerPlugin : Plugin<Project> {

    private val resolverDependencyProvider: ProjectResolverDependencyProvider = ProjectResolverDependencyProviderImpl()

    private val androidDependencyProvider: AndroidDependencyProvider = AndroidDependencyProvider()

    private val projectTypeResolver: AndroidProjectTypeResolver
        get() = resolverDependencyProvider.androidProjectTypeResolver()

    override fun apply(project: Project) {

        androidDependencyProvider.buildTrackerHelper(
            projectTypeResolver.type(project)
        ).withExtensions(project) {
            println(it)
        }

        project.tasks.register(
            "operatingSystem",
            BuildEnvironmentMetadataTask::class.java,
            EnvironmentInformationDependencyProviderImpl(project)
        )
        //project.tasks.findByName("clean")//?
    }
}
