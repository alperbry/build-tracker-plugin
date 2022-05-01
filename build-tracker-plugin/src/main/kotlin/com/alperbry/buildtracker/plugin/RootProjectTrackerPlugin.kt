package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.data.extension.BuildTrackerExtension.Companion.buildTrackerExtensions
import com.alperbry.buildtracker.di.OneTimeTaskProviderImpl
import com.alperbry.buildtracker.di.ProjectInformationDependencyProviderImpl
import com.alperbry.buildtracker.di.VCSDependencyProviderImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

open class RootProjectTrackerPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val buildTrackerExtension = project.buildTrackerExtensions()

        val oneTimeTaskManagerProvider = OneTimeTaskProviderImpl(
            projectInformationDependencyProvider = ProjectInformationDependencyProviderImpl(
                VCSDependencyProviderImpl(project)
            ),
            buildTrackerExtension = buildTrackerExtension
        )

        project.gradle.addBuildListener(
            oneTimeTaskManagerProvider.oneTimeTaskManager()
        )
    }
}
