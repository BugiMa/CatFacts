package com.bugima.catfacts.data.remote.dto

data class CatFactListItemDto(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val deleted: Boolean,
    val source: String,
    val status: Status,
    val text: String,
    val type: String,
    val updatedAt: String,
    val used: Boolean,
    val user: String
) {
    data class Status(
        val sentCount: Int,
        val verified: Any
    )
}