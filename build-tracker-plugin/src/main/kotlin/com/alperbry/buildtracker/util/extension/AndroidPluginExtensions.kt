package com.alperbry.buildtracker.util.extension

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

inline fun <reified T : BaseExtension> extension(project: Project): T {
    return project.extensions.getByType(T::class.java)
}
