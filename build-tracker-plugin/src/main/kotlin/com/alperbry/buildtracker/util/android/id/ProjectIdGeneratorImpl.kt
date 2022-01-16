package com.alperbry.buildtracker.util.android.id

import com.alperbry.buildtracker.util.vcs.VCSInfoResolver
import org.gradle.api.Project

class ProjectIdGeneratorImpl(
    private val vcsResolver: VCSInfoResolver,
    private val project: Project
) : ProjectIdGenerator {

    override fun id(): String {
        val repositoryDirectory = vcsResolver.repositoryDirectory()
        val currentDirectory = project.projectDir

        val repositoryName = repositoryDirectory.name
        val projectFolderName = currentDirectory.name

        return "${repositoryName.capitalize()}${projectFolderName.capitalize()}"
    }
}
