package com.alperbry.buildtracker.util.extension

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

inline fun <reified T : BaseExtension> androidExtension(project: Project): T {
    return project.extensions.getByType(T::class.java)
}

inline fun <reified T> extension(project: Project): T {
    return project.extensions.getByType(T::class.java)
}
