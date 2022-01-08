package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.BuildTrackerAndroidExtensions
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.LibraryVariant
import java.io.File

class AndroidExtensionMapperImpl : AndroidExtensionMapper {

    override fun map(variant: ApplicationVariant): BuildTrackerAndroidExtensions {
        val outputFiles = mutableListOf<File>()

        variant.outputs.all {
            outputFiles.add(it.outputFile.absoluteFile)
        }

        return BuildTrackerAndroidExtensions(
            outputFiles = outputFiles,
            assembleTask = variant.assembleProvider.name
        )
    }

    override fun map(variant: LibraryVariant): BuildTrackerAndroidExtensions {
        val outputFiles = mutableListOf<File>()

        variant.outputs.all {
            outputFiles.add(it.outputFile.absoluteFile)
        }

        return BuildTrackerAndroidExtensions(
            outputFiles = outputFiles,
            assembleTask = variant.assembleProvider.name
        )
    }
}
