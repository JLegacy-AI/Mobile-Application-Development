package com.jlegacyai.contentproviderjokesdatabase.database.model

enum class Type(val value: String) {
    SINGLE("single"),
    TWOPART("twopart"),
    ALL("${SINGLE.value},${TWOPART.value}")
}