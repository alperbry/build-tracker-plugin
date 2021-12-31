package com.alperbry.buildtracker.system

import java.util.Locale


class SystemRepositoryImpl : SystemRepository {

    override val osName: String
        get() = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH)

    override val architecture: String
        get() = System.getProperty("os.arch")

    override val version: String
        get() = System.getProperty("os.version")
}
