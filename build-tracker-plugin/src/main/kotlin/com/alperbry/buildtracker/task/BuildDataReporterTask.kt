package com.alperbry.buildtracker.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BuildDataReporterTask : DefaultTask() {

    @TaskAction
    fun execute() {
        println("reporting task")
    }
}
