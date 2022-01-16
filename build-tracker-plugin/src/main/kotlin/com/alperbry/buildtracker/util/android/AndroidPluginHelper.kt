package com.alperbry.buildtracker.util.android

import org.gradle.api.Project

interface AndroidPluginHelper <T> {
    fun withExtensions(project: Project, block: (T) -> Unit)
}
