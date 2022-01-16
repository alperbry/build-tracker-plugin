package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.AndroidProjectType
import org.gradle.api.plugins.PluginAware

class AndroidProjectTypeResolverImpl : AndroidProjectTypeResolver {

    override fun isAndroid(pluginAware: PluginAware): Boolean {
        return isAndroidApplication(pluginAware) || isAndroidLibrary(pluginAware) || isAndroidFeature(pluginAware)
    }

    override fun type(pluginAware: PluginAware): AndroidProjectType {
        return when {
            isAndroidApplication(pluginAware) -> AndroidProjectType.APPLICATION
            isAndroidLibrary(pluginAware) -> AndroidProjectType.LIBRARY
            isAndroidFeature(pluginAware)-> AndroidProjectType.FEATURE
            else -> throw IllegalArgumentException()
        }
    }

    private fun isAndroidApplication(pluginAware: PluginAware) = pluginAware.hasPlugin("com.android.application")

    private fun isAndroidLibrary(pluginAware: PluginAware) = pluginAware.hasPlugin("com.android.library")

    private fun isAndroidFeature(pluginAware: PluginAware) = pluginAware.hasPlugin("com.android.dynamic-feature")

    private fun PluginAware.hasPlugin(id: String) = plugins.hasPlugin(id)
}
