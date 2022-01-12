package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import com.alperbry.buildtracker.util.git.GitResolver
import com.alperbry.buildtracker.util.git.GitResolverImpl
import org.gradle.api.Project

interface VCSDependencyProvider {

    fun gitResolver(): GitResolver
}

class VCSDependencyProviderImpl(
    private val project: Project
) : VCSDependencyProvider {

    private val gitResolver by lazy {
        GitResolverImpl(CommandLineExecutorImpl(project))
    }

    override fun gitResolver() = gitResolver
}
