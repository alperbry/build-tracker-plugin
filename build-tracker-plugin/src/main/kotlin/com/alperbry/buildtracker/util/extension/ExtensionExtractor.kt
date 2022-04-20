package com.alperbry.buildtracker.util.extension

import com.alperbry.buildtracker.data.android.BaseModuleExtension
import org.gradle.api.Project

interface ExtensionExtractor <T : BaseModuleExtension> {
    fun withExtensions(project: Project, block: (T) -> Unit)
}
