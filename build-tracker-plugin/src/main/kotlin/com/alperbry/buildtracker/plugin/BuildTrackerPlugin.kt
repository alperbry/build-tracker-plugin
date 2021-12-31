package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.di.EnvironmentInformationDependencyProviderImpl
import com.alperbry.buildtracker.task.BuildEnvironmentMetadataTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildTrackerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register(
            "operatingSystem",
            BuildEnvironmentMetadataTask::class.java,
            EnvironmentInformationDependencyProviderImpl()
        )
    }
}
