package com.alperbry.buildtracker.data

import com.alperbry.buildtracker.data.android.AndroidProjectType

sealed interface GradleProjectType {

    data class Android(val projectType: AndroidProjectType) : GradleProjectType

    object Other : GradleProjectType
}
