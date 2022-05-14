package com.bugima.catfacts.domain.mapper

import com.bugima.catfacts.data.remote.dto.CatFactDetailsDto
import com.bugima.catfacts.data.remote.dto.CatFactListItemDto
import com.bugima.catfacts.domain.model.CatFact
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun CatFactListItemDto.toDomain(): CatFact
{
    return CatFact(
        this._id,
        this.text,
        this.updatedAt
    )
}

fun List<CatFactListItemDto>.toDomain(): List<CatFact> {
    return this.map { it.toDomain() }
}

fun CatFactDetailsDto.toDomain(): CatFact
{
    return CatFact(
        this._id,
        this.text,
        this.updatedAt.formatStringDate()
    )
}

private fun String.formatStringDate(): String {
    val oldFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT)
    val newFormat = SimpleDateFormat("dd.MM.yyyy 'at' hh:mm", Locale.ROOT)
    return try {
        newFormat.format(oldFormat.parse(this)!!)
    } catch (e: ParseException){
        e.message.toString()
    }
}