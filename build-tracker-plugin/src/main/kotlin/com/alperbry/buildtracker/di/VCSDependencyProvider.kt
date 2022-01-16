package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.data.VCSType.GIT
import com.alperbry.buildtracker.data.VCSType.OTHER
import com.alperbry.buildtracker.util.commandline.CommandLineExecutorImpl
import com.alperbry.buildtracker.util.git.GitResolverImpl
import com.alperbry.buildtracker.util.vcs.DummyVCSInfoResolver
import com.alperbry.buildtracker.util.vcs.VCSInfoResolver
import com.alperbry.buildtracker.util.vcs.VCSTypeResolverImpl
import org.gradle.api.Project

interface VCSDependencyProvider {

    fun informationResolver(): VCSInfoResolver
}

class VCSDependencyProviderImpl(
    project: Project,
) : VCSDependencyProvider {

    private val commandLineExecutor = CommandLineExecutorImpl(project)

    private val typeResolver = VCSTypeResolverImpl(commandLineExecutor)

    private val gitResolver by lazy {
        GitResolverImpl(commandLineExecutor)
    }

    override fun informationResolver() = when (typeResolver.type()) {
        GIT -> gitResolver
        OTHER -> DummyVCSInfoResolver()
    }
}
