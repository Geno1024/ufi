@file:Suppress("unused")

package g.ufi

import java.io.InputStream
import java.io.OutputStream

/**
 * Stream
 */
interface S
{
    fun read(i: InputStream): RA
    fun write(o: OutputStream)
}
