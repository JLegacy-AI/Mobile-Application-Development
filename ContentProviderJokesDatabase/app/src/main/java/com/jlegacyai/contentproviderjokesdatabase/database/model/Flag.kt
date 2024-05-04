package com.jlegacyai.contentproviderjokesdatabase.database.model

enum class Flag(val value: String) {
    EXPLICIT("explicit"),
    NSFW("nsfw"),
    POLITICAL("political"),
    RACIST("racist"),
    RELIGIOUS("religious"),
    SEXIST("sexist"),
    ALL("${NSFW.value},${RELIGIOUS.value},${POLITICAL.value},${RACIST.value},${SEXIST.value},${EXPLICIT.value}")
}