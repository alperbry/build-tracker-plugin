package com.alperbry.buildtracker.util.gradle

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle

open class SimpleBuildListener : BuildListener {

    override fun beforeSettings(settings: Settings) {
        super.beforeSettings(settings)
    }

    override fun settingsEvaluated(settings: Settings) {
        // No-op
    }

    override fun projectsLoaded(gradle: Gradle) {
        // No-op
    }

    override fun projectsEvaluated(gradle: Gradle) {
        // No-op
    }

    override fun buildFinished(buildResult: BuildResult) {
        // No-op
    }
}
