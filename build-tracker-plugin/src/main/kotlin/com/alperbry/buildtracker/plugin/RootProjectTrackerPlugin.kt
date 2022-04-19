package com.alperbry.buildtracker.plugin

import com.alperbry.buildtracker.di.OneTimeTaskProviderImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

open class RootProjectTrackerPlugin : Plugin<Project> {

    private val oneTimeTaskManagerProvider = OneTimeTaskProviderImpl()

    override fun apply(project: Project) {

        project.gradle.addBuildListener(
            oneTimeTaskManagerProvider.oneTimeTaskManager()
        )
    }
}
