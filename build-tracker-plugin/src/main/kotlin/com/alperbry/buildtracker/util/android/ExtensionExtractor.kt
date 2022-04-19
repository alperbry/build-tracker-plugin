package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.BaseProjectExtension
import org.gradle.api.Project

interface ExtensionExtractor <T : BaseProjectExtension> {
    fun withExtensions(project: Project, block: (T) -> Unit)
}
