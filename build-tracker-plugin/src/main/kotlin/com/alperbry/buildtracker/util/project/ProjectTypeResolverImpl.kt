package com.alperbry.buildtracker.util.project

import com.alperbry.buildtracker.data.GradleProjectType
import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolver
import org.gradle.api.Project
import org.gradle.api.plugins.PluginAware

class ProjectTypeResolverImpl(
    private val androidResolver: AndroidProjectTypeResolver
) : ProjectTypeResolver {

    override fun type(pluginAware: Project): GradleProjectType {
        return if (androidResolver.isAndroid(pluginAware)) {
            GradleProjectType.ANDROID
        } else {
            GradleProjectType.OTHER
        }
    }
}
