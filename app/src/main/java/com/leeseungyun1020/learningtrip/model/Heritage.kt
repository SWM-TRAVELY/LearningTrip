package com.leeseungyun1020.learningntripapitest

data class Heritage(
    val id: String,
    val name: String,
    val imageURL: String,
)

data class SimpleHeritage(
    val id: String,
    val name: String,
    val imageURL: String,
)

fun Heritage.toSimpleHeritage(): SimpleHeritage {
    return SimpleHeritage(
        id = id,
        name = name,
        imageURL = imageURL,
    )
}