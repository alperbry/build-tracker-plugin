package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.cache.AndroidBuildInformationCache
import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildMetadata

interface CacheDependencyProvider {

    fun androidBuildCache(): BuildInformationCache<AndroidBuildMetadata>
}

class CacheDependencyProviderImpl : CacheDependencyProvider {

    override fun androidBuildCache(): BuildInformationCache<AndroidBuildMetadata> {
        return AndroidBuildInformationCache
    }
}
