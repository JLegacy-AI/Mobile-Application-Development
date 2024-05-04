package com.jlegacyai.contentproviderjokesdatabase.database.model

data class Joke(
    val id: Int,
    val type: Type,
    val category: Category,
    val joke: String,
    val setup: String,
    val delivery: String,
    val lang: Language,
)