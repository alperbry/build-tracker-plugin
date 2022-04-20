package com.alperbry.buildtracker.util.extension

import com.alperbry.buildtracker.data.android.BaseModuleExtension
import org.gradle.api.Project

class ModuleExtensionExtractor : ExtensionExtractor<BaseModuleExtension> {

    override fun withExtensions(project: Project, block: (BaseModuleExtension) -> Unit) {
        //TODO("Not yet implemented")
    }
}
