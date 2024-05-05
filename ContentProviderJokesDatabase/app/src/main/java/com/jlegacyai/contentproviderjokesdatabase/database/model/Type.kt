package com.jlegacyai.contentproviderjokesdatabase.database.model

enum class Type(val value: String) {
    SINGLE("Single"),
    TWOPART("Two Part"),
    ALL("${SINGLE.value},${TWOPART.value}")
}