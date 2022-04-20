package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.util.project.ProjectInformationResolver
import com.alperbry.buildtracker.util.project.ProjectInformationResolverImpl

interface ProjectInformationDependencyProvider {

    fun projectInformationResolver(): ProjectInformationResolver
}

class ProjectInformationDependencyProviderImpl(
    private val vcsDependencyProvider: VCSDependencyProvider
) : ProjectInformationDependencyProvider {

    private val informationResolver by lazy {
        ProjectInformationResolverImpl(vcsDependencyProvider.informationResolver())
    }

    override fun projectInformationResolver(): ProjectInformationResolver {
        return informationResolver
    }
}
