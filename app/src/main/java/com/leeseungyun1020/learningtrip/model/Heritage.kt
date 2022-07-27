package com.leeseungyun1020.learningntripapitest

data class Heritage(
    val id: String,
    val name: String,
    val image: String,
)

data class SimpleHeritage(
    val id: String,
    val name: String,
    val image: String,
)

fun Heritage.toSimpleHeritage(): SimpleHeritage {
    return SimpleHeritage(
        id = id,
        name = name,
        image = image,
    )
}