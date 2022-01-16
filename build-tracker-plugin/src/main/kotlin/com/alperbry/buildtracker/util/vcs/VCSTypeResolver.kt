package com.alperbry.buildtracker.util.vcs

import com.alperbry.buildtracker.data.VCSType

interface VCSTypeResolver {

    fun type(): VCSType
}
