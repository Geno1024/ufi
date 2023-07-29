package com.geno1024.ufiold

import java.io.File

interface UFI
{
    val extension: String

    val mime: String

    val rootDs: Any?

    fun deserialize(file: File): UFI

    fun deserialize(content: String): UFI

    fun serialize(): ByteArray

    companion object
    {
        const val BINARY_FILE_ERROR = "Binary file should not be read as string."
    }
}
