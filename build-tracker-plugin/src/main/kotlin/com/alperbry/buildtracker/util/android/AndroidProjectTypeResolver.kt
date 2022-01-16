package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.AndroidProjectType
import org.gradle.api.plugins.PluginAware

interface AndroidProjectTypeResolver {

    fun isAndroid(pluginAware: PluginAware): Boolean

    fun type(pluginAware: PluginAware): AndroidProjectType
}
