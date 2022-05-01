package com.alperbry.buildtracker.util.project

import com.alperbry.buildtracker.data.ProjectInfo
import com.alperbry.buildtracker.data.extension.BuildTrackerExtension
import com.alperbry.buildtracker.util.vcs.VCSInfoResolver
import org.gradle.api.Project

interface ProjectInformationResolver {

    fun projectInfo(
        project: Project,
        buildTrackerExtension: BuildTrackerExtension
    ): ProjectInfo
}

class ProjectInformationResolverImpl(
    private val vcsInfoResolver: VCSInfoResolver
) : ProjectInformationResolver {

    override fun projectInfo(
        project: Project,
        buildTrackerExtension: BuildTrackerExtension
    ): ProjectInfo {

        val id = buildTrackerExtension.projectId.ifEmpty { project.name }

        return ProjectInfo(
            id = id,
            stateIdentifier = vcsInfoResolver.stateIdentifier()
        )
    }
}
