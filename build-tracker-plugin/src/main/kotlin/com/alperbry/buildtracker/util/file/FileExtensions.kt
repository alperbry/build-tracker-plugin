package com.alperbry.buildtracker.util.file

import java.io.File

/**
 * Ensures that the parent directories are also created recursively.
 */
internal fun File.safeChild(child: String): File {
    return File(this, child).also {
        it.parentFile.mkdirs()
    }
}
