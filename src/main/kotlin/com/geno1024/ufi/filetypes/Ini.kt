package com.geno1024.ufi.filetypes

import com.geno1024.ufi.UFI
import java.io.File
import java.util.LinkedList

object Ini : UFI
{
    override val extension: String = "ini"

    override val mime: String = ""

    override val rootDs: MutableList<Section> = mutableListOf()

    override fun deserialize(content: String): UFI
    {
        content.lines().forEach {
            val line = it.strip()
            if (line.startsWith("[") and line.endsWith("]"))
                rootDs.add(Section(line.drop(1).dropLast(1), mutableListOf()))
            else
                with(line.split("=")) {
                    rootDs.last().kvp.add(Pair(this[0], this[1]))
                }
        }
        return this
    }

    override fun serialize(): ByteArray
    {
        TODO("Not yet implemented")
    }

    data class Section(val name: String, val kvp: MutableList<Pair<String, String>>)
}
