package com.alperbry.buildtracker.util.taskexecution

import com.alperbry.buildtracker.util.timer.Timer
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class TimeTrackerExecutionListener(
    private val timer: Timer,
    private val finalTaskName: String
) : TaskExecutionListener {

    init {
        timer.start()
    }

    override fun beforeExecute(task: Task) {
        if (task.name == finalTaskName) {
            print("FINAL DURATION IN MS: ${timer.duration()}ms")
        }
    }

    override fun afterExecute(task: Task, taskState: TaskState) {
        // No-op.
    }
}
