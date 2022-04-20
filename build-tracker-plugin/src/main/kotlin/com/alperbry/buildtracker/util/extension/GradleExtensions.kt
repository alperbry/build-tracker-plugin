package com.alperbry.buildtracker.util.extension

import org.gradle.api.Project

internal inline fun Project.afterEachSubProjectEvaluated(crossinline block: (Project) -> Unit) {
    subprojects { subproject ->
        subproject.afterEvaluate { evaluatedChild ->
            block(evaluatedChild)
        }
    }
}

internal fun Project.breadthWiseProjectBranch(): List<Project> {
    fun Project.hierarchyList(): List<Project> {
        return if (this == rootProject) {
            listOf(this)
        } else {
            rootProject.hierarchyList() + this
        }
    }

    return hierarchyList()
}
