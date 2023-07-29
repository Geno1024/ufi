package com.geno1024.ufiold.filetypes

import com.geno1024.ufiold.UFI
import java.io.File

object TTF : UFI
{
    override val extension: String = "ttf"
    override val mime: String = ""
    override val rootDs: Struct = Struct(0, 0, 0)

    override fun deserialize(file: File): TTF
    {

        file.readBytes().apply {
            println(this[0])
        }
        return this
    }

    override fun deserialize(content: String): TTF = throw NotImplementedError(UFI.BINARY_FILE_ERROR)

    override fun serialize(): ByteArray
    {
        TODO("Not yet implemented")
    }

    data class Struct(
        val unknown0: Int,
        val unknown1: Int,
        val unknown2: Int
    )
    {

    }

    @JvmStatic
    fun main(args: Array<String>)
    {
        deserialize(File("test/arial.ttf"))
    }
}
