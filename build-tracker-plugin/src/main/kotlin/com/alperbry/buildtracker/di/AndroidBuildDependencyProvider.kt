package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.apk.ApkRepositoryImpl
import com.alperbry.buildtracker.util.android.apk.ApkResolver
import com.alperbry.buildtracker.util.android.apk.ApkResolverImpl
import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import org.gradle.api.Project

interface AndroidBuildDependencyProvider {

    fun apkResolver(): ApkResolver
}

class AndroidBuildDependencyProviderImpl(
    private val project: Project
) : AndroidBuildDependencyProvider {

    private val apkResolver by lazy {
        ApkResolverImpl(ApkRepositoryImpl(CommandLineExecutorImpl(project)))
    }

    override fun apkResolver() = apkResolver
}
