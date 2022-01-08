package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.LibraryVariant

interface AndroidExtensionMapper {

    fun map(variant: ApplicationVariant): BuildTrackerAndroidExtensions

    fun map(variant: LibraryVariant): BuildTrackerAndroidExtensions
}
