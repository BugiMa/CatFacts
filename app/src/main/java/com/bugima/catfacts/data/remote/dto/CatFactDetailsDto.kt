package com.bugima.catfacts.data.remote.dto

data class CatFactDetailsDto(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val deleted: Boolean,
    val source: String,
    val status: CatFactListItemDto.Status,
    val text: String,
    val type: String,
    val updatedAt: String,
    val used: Boolean,
    val user: User
) {
    data class Status(
        val feedback: String,
        val sentCount: Int,
        val verified: Boolean
    )

    data class User(
        val _id: String,
        val name: Name,
        val photo: String
    ) {
        data class Name(
            val first: String,
            val last: String
        )
    }
}