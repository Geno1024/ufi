package com.geno1024.ufi.filetypes

import com.geno1024.ufi.UFI
import java.io.File

object CSV : UFI
{
    data class Struct(
        var rows: MutableList<Row> = mutableListOf(Row())
    ) : UFI.Struct
    {
        data class Row(
            var cells: MutableList<Cell> = mutableListOf(Cell())
        )
        {
            data class Cell(
                var value: StringBuilder = StringBuilder()
            )
        }
    }

    override val mime: String = "text/csv"
    override val extension: String = "csv"
    override val ds: Struct = Struct()

    var cellSeparator = ','
    var rowSeparator = '\n'
    var quote = '"'

    override fun deserialize(file: File): Struct = Struct().apply {
        var maybeDoubleQuoteInQuote = false
        var inQuote = false
        var cellBegin = true
        file.readText().forEach {
            if (cellBegin)
            {
                cellBegin = false
                if (it == quote)
                {
                    inQuote = true
                }
                else
                {
                    rows.last().cells.last().value.append(it)
                }
            }
            else
            {
                if (inQuote)
                {
                    if (maybeDoubleQuoteInQuote)
                    {
                        maybeDoubleQuoteInQuote = false
                        if (it == quote)
                        {
                            rows.last().cells.last().value.append(it)
                        }
                        else
                        {
                            inQuote = false
                            cellBegin = true
                            rows.last().cells.add(Struct.Row.Cell())
                        }
                    }
                    else
                    {
                        if (it == quote)
                        {
                            maybeDoubleQuoteInQuote = true
                        }
                        else
                        {
                            rows.last().cells.last().value.append(it)
                        }
                    }
                }
                else
                {
                    when (it)
                    {
                        rowSeparator ->
                        {
                            cellBegin = true
                            rows.add(Struct.Row())
                        }
                        cellSeparator ->
                        {
                            cellBegin = true
                            rows.last().cells.add(Struct.Row.Cell())
                        }
                        else ->
                        {
                            rows.last().cells.last().value.append(it)
                        }
                    }
                }
            }
        }
    }

    override fun serialize(struct: UFI.Struct): ByteArray
    {
        TODO("Not yet implemented")
    }
}
