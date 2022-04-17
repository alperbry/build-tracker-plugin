package com.alperbry.buildtracker.util.extension

import org.gradle.api.Project

internal inline fun Project.afterEachSubProjectEvaluated(crossinline block: (Project) -> Unit) {
    subprojects {
        afterEvaluate { evaluatedChild ->
            block(evaluatedChild)
        }
    }
}
