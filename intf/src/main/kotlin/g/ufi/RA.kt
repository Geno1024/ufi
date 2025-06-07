@file:Suppress("unused")

package g.ufi

import java.io.RandomAccessFile

/**
 * Random Access
 */
interface RA
{
    fun read(i: RandomAccessFile): RA
    fun write(o: RandomAccessFile)
}
