package com.alperbry.buildtracker.util.taskexecution

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.util.timer.Timer
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.tasks.TaskState

class TimeTracker(
    private val timer: Timer,
    private val finalTaskName: String,
    private val cache: BuildInformationCache<*>
) : TaskExecutionListener {

    init {
        timer.start()
    }

    override fun beforeExecute(task: Task) {
        if (task.name == finalTaskName) {
            cache.durationInMs = timer.duration()
        }
    }

    override fun afterExecute(task: Task, taskState: TaskState) {
        // No-op.
    }
}
