package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.cache.AndroidBuildInformationCache
import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildInfo

interface CacheDependencyProvider {

    fun androidBuildCache(): BuildInformationCache<AndroidBuildInfo>
}

class CacheDependencyProviderImpl : CacheDependencyProvider {

    override fun androidBuildCache(): BuildInformationCache<AndroidBuildInfo> {
        return AndroidBuildInformationCache
    }
}
