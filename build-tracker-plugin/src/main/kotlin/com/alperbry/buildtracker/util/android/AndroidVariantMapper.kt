package com.alperbry.buildtracker.util.android

import com.alperbry.buildtracker.data.android.Variant
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.LibraryVariant

interface AndroidVariantMapper {

    fun map(variant: ApplicationVariant): Variant

    fun map(variant: LibraryVariant): Variant
}
