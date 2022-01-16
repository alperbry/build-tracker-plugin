package com.alperbry.buildtracker.util.vcs

import java.io.File

interface VCSInfoResolver {

    fun stateIdentifier(): String

    fun repositoryDirectory(): File
}

class DummyVCSInfoResolver : VCSInfoResolver {
    override fun stateIdentifier(): String {
        return ""
    }

    override fun repositoryDirectory(): File {
        return File(".")
    }
}
