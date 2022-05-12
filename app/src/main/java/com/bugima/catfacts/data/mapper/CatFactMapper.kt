package com.bugima.catfacts.data.mapper

import com.bugima.catfacts.data.remote.dto.CatFactDto
import com.bugima.catfacts.domain.model.CatFact
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun CatFactDto.toCatFact(): CatFact
{
    return CatFact(
        this._id,
        this.text,
        this.updatedAt
    )
}