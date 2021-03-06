package com.alperbry.buildtracker.util.vcs

import com.alperbry.buildtracker.data.VCSType
import com.alperbry.buildtracker.data.VCSType.GIT
import com.alperbry.buildtracker.data.VCSType.OTHER
import com.alperbry.buildtracker.util.commandline.CommandLineExecutor
import com.alperbry.buildtracker.util.commandline.safeExecute

class VCSTypeResolverImpl(
    private val executor: CommandLineExecutor
) : VCSTypeResolver {

    override fun type(): VCSType {
        return if (isGitRepo()) GIT else OTHER
    }

    private fun isGitRepo(): Boolean {
        return executor.safeExecute(
            "git",
            "rev-parse",
            "--is-inside-work-tree"
        ).trim() == "true"
    }
}
