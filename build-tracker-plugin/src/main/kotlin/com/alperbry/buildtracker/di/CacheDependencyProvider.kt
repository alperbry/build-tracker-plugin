package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.cache.AndroidBuildInformationCache
import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.android.AndroidBuildOutputInfo

interface CacheDependencyProvider {

    fun androidBuildCache(): BuildInformationCache<AndroidBuildOutputInfo>
}

object CacheDependencyProviderImpl : CacheDependencyProvider {

    private val cache by lazy {
        AndroidBuildInformationCache()
    }

    override fun androidBuildCache(): BuildInformationCache<AndroidBuildOutputInfo> {
        return cache
    }
}
