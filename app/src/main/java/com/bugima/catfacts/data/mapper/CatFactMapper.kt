package com.bugima.catfacts.data.mapper

import com.bugima.catfacts.data.remote.dto.CatFactDto
import com.bugima.catfacts.domain.model.CatFact

fun CatFactDto.toDomain(): CatFact
{
    return CatFact(
        this._id,
        this.text,
        this.updatedAt
    )
}

fun List<CatFactDto>.toDomain(): List<CatFact> {
    return this.map { it.toDomain() }
}