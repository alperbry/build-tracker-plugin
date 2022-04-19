package com.alperbry.buildtracker.util.extension

import com.alperbry.buildtracker.data.android.BaseProjectExtension
import com.alperbry.buildtracker.util.android.ExtensionExtractor
import org.gradle.api.Project

class ProjectExtensionExtractor : ExtensionExtractor<BaseProjectExtension> {

    override fun withExtensions(project: Project, block: (BaseProjectExtension) -> Unit) {
        //TODO("Not yet implemented")
    }
}
