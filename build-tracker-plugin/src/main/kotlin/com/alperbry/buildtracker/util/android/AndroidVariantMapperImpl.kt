package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.Variant
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.LibraryVariant
import java.io.File

class AndroidVariantMapperImpl : AndroidVariantMapper {

    override fun map(variant: ApplicationVariant): Variant {
        val outputFiles = mutableListOf<File>()

        variant.outputs.all {
            outputFiles.add(it.outputFile.absoluteFile)
        }

        return Variant(
            id = variant.applicationId,
            variantName = variant.name,
            flavorName =  variant.flavorName,
            versionCode = variant.versionCode,
            outputFiles = outputFiles,
            assembleTask = variant.assembleProvider.name
        )
    }

    override fun map(variant: LibraryVariant): Variant {
        val outputFiles = mutableListOf<File>()

        variant.outputs.all {
            outputFiles.add(it.outputFile.absoluteFile)
        }

        return Variant(
            id = "",
            variantName = variant.name,
            flavorName = variant.flavorName,
            outputFiles = outputFiles,
            versionCode = -1, // fixme
            assembleTask = variant.assembleProvider.name
        )
    }
}
