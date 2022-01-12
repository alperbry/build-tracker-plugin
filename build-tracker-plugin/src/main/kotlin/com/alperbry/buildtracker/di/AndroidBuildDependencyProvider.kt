package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.apk.ApkRepositoryImpl
import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.AndroidProjectType.APPLICATION
import com.alperbry.buildtracker.data.android.AndroidProjectType.FEATURE
import com.alperbry.buildtracker.data.android.AndroidProjectType.LIBRARY
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import com.alperbry.buildtracker.util.android.apk.ApkBuildResolverImpl
import com.alperbry.buildtracker.util.android.library.LibraryBuildResolverImpl
import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import org.gradle.api.Project

interface AndroidBuildDependencyProvider {

    fun buildResolver(projectType: AndroidProjectType): AndroidBuildResolver
}

class AndroidBuildDependencyProviderImpl(
    private val project: Project
) : AndroidBuildDependencyProvider {

    private val apkResolver by lazy {
        ApkBuildResolverImpl(ApkRepositoryImpl(CommandLineExecutorImpl(project)))
    }

    private val libraryResolver by lazy {
        LibraryBuildResolverImpl() // todo fixme
    }

    override fun buildResolver(projectType: AndroidProjectType) = when (projectType) {
        APPLICATION -> apkResolver
        LIBRARY -> libraryResolver
        FEATURE -> libraryResolver
    }
}
