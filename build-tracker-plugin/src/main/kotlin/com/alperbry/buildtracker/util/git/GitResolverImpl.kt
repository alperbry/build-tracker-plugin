package com.alperbry.buildtracker.util.git

import com.alperbry.buildtracker.util.commandline.CommandLineExecutor
import com.alperbry.buildtracker.util.vcs.VCSInfoResolver
import java.io.File

class GitResolverImpl(
    private val executor: CommandLineExecutor
) : VCSInfoResolver {

    override fun stateIdentifier(): String {
        return executor.execute(
            "git",
            "rev-parse",
            "HEAD"
        ).trim()
    }

    override fun repositoryDirectory(): File {
        val directory = executor.execute(
            "git",
            "rev-parse",
            "--show-toplevel"
        ).trim()

        return File(directory)
    }
}
