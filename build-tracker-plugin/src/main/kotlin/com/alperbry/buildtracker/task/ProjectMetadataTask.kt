package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtracker.util.project.ProjectInformationResolver
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class ProjectMetadataTask @Inject constructor(
    private val projectInformationResolver: ProjectInformationResolver,
    private val buildTrackerExtension: BuildTrackerExtension,
    private val cache: BuildInformationCache<*>
) : DefaultTask() {

    @TaskAction
    fun execute() {
        cache.projectInfo = projectInformationResolver.projectInfo(
            project, buildTrackerExtension
        )
    }
}
