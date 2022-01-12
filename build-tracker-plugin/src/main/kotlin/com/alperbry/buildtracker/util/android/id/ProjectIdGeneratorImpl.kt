package com.alperbry.buildtracker.util.android.id

import com.alperbry.buildtracker.util.git.GitResolver
import org.gradle.api.Project

class ProjectIdGeneratorImpl(
    private val gitResolver: GitResolver,
    private val project: Project
) : ProjectIdGenerator {

    override fun id(): String {
        val repositoryDirectory = gitResolver.repositoryDirectory()
        val currentDirectory = project.projectDir

        val repositoryName = repositoryDirectory.name
        val projectFolderName = currentDirectory.name

        return "${repositoryName.capitalize()}${projectFolderName.capitalize()}"
    }
}
