package com.alperbry.buildtracker.util.android.id

import com.alperbry.buildtracker.util.extension.breadthWiseProjectBranch
import org.gradle.api.Project

class ProjectIdGeneratorImpl(
    private val project: Project
) : ProjectIdGenerator {

    override fun id() = project
        .breadthWiseProjectBranch()
        .joinToString(separator = ":", transform = Project::getName)
}
