package com.alperbry.buildtracker.util.taskexecution

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState
import java.time.Instant

class TimeTrackerExecutionListener : TaskExecutionListener, BuildListener {

    private val taskHashToStartMap = mutableMapOf<Int, Long>()

    private val durations = mutableListOf<Long>()

    override fun beforeExecute(task: Task) {
        taskHashToStartMap.put(task.hashCode(), System.currentTimeMillis())
    }

    override fun afterExecute(task: Task, taskState: TaskState) {

        if (taskHashToStartMap.containsKey(task.hashCode()).not()) return

        val now = System.currentTimeMillis()
        val result = now - taskHashToStartMap[task.hashCode()]!!
        durations.add(result)
        println("${task.name} duration: $result ${taskState.executed}")
    }

    override fun buildFinished(buildResult: BuildResult) {
        println(durations.sum())
    }

    override fun settingsEvaluated(p0: Settings) {
        //TODO("Not yet implemented")
    }

    override fun projectsLoaded(p0: Gradle) {
        //TODO("Not yet implemented")
    }

    override fun projectsEvaluated(p0: Gradle) {
        //TODO("Not yet implemented")
    }
}
