package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolver
import com.alperbry.buildtracker.util.android.AndroidProjectTypeResolverImpl
import com.alperbry.buildtracker.util.project.ProjectTypeResolver
import com.alperbry.buildtracker.util.project.ProjectTypeResolverImpl

interface ProjectResolverDependencyProvider {

    fun projectTypeResolver(): ProjectTypeResolver

    fun androidProjectTypeResolver(): AndroidProjectTypeResolver
}

class ProjectResolverDependencyProviderImpl : ProjectResolverDependencyProvider {

    private val androidProjectTypeResolver = AndroidProjectTypeResolverImpl()

    private val projectTypeResolver = ProjectTypeResolverImpl(androidProjectTypeResolver)

    override fun projectTypeResolver() = projectTypeResolver

    override fun androidProjectTypeResolver() = androidProjectTypeResolver
}
