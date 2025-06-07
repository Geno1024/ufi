package g.ufi

import java.io.InputStream
import java.io.OutputStream

/**
 * Stream
 */
interface S
{
    fun write(output: OutputStream)
    fun read(input: InputStream): S
}
