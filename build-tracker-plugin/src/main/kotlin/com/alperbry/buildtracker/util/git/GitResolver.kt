package com.alperbry.buildtracker.util.git

import java.io.File

interface GitResolver {

    fun isGitRepository(): Boolean

    fun revision(): String

    fun repositoryDirectory(): File
}
