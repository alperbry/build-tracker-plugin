package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.apk.ApkDataSourceImpl
import com.alperbry.buildtracker.data.android.AndroidProjectType
import com.alperbry.buildtracker.data.android.AndroidProjectType.APPLICATION
import com.alperbry.buildtracker.data.android.AndroidProjectType.FEATURE
import com.alperbry.buildtracker.data.android.AndroidProjectType.LIBRARY
import com.alperbry.buildtracker.util.android.AndroidBuildResolver
import com.alperbry.buildtracker.util.android.apk.ApkBuildResolverImpl
import com.alperbry.buildtracker.util.android.id.ProjectIdGeneratorImpl
import com.alperbry.buildtracker.util.android.library.LibraryBuildResolverImpl
import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import com.alperbry.buildtracker.util.git.GitResolverImpl
import com.alperbry.buildtracker.util.vcs.VCSInfoResolver
import org.gradle.api.Project

interface AndroidBuildDependencyProvider {

    fun buildResolver(projectType: AndroidProjectType): AndroidBuildResolver
}

class AndroidBuildDependencyProviderImpl(
    private val project: Project,
    private val vcsProvider: VCSDependencyProvider
) : AndroidBuildDependencyProvider {

    private val commandLineExecutor by lazy {
        CommandLineExecutorImpl(project)
    }

    private val apkResolver by lazy {
        ApkBuildResolverImpl(ApkDataSourceImpl(commandLineExecutor))
    }

    private val vcsResolver: VCSInfoResolver
        get() = vcsProvider.informationResolver()

    private val libraryResolver by lazy {
        LibraryBuildResolverImpl(ProjectIdGeneratorImpl(vcsResolver, project))
    }

    override fun buildResolver(projectType: AndroidProjectType) = when (projectType) {
        APPLICATION -> apkResolver
        LIBRARY -> libraryResolver
        FEATURE -> libraryResolver
    }
}
