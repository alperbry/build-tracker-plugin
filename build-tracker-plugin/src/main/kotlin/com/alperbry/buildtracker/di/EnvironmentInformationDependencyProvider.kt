package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.system.SystemRepositoryImpl
import com.alperbry.buildtracker.util.environment.OperatingSystemResolver
import com.alperbry.buildtracker.util.environment.OperatingSystemResolverImpl

interface EnvironmentInformationDependencyProvider {

    fun operatingSystemResolver(): OperatingSystemResolver
}

class EnvironmentInformationDependencyProviderImpl : EnvironmentInformationDependencyProvider {
    override fun operatingSystemResolver() = OperatingSystemResolverImpl(SystemRepositoryImpl())
}
