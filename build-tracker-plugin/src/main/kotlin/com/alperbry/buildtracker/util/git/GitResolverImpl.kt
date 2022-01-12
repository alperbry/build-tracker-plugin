package com.alperbry.buildtracker.util.git

import com.alperbry.buildtracker.util.commandline.CommandLineExecutor
import java.io.File

class GitResolverImpl(
    private val executor: CommandLineExecutor
) : GitResolver {

    override fun isGitRepository(): Boolean {
        TODO("Not yet implemented")
    }

    override fun revision(): String {
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
