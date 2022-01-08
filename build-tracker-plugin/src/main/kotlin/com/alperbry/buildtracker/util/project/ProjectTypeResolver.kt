package com.alperbry.buildtracker.util.project

import com.alperbry.buildtracker.data.GradleProjectType
import org.gradle.api.Project
import org.gradle.api.plugins.PluginAware

interface ProjectTypeResolver {

    fun type(pluginAware: Project): GradleProjectType
}
