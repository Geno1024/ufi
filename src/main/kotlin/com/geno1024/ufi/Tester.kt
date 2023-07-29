package com.geno1024.ufi

import com.geno1024.ufi.filetypes.TTF
import java.io.File

object Tester
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        TTF.deserialize(File("./test/arial.ttf")).apply {
            println(this)
        }
    }
}
