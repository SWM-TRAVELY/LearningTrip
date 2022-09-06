package com.leeseungyun1020.learningtrip.model

data class Heritage(
    val id: Int,
    val name: String,
    val imageURL: String?,
    val description: String,
    val type: String,
    val category: String,
)

data class SimpleHeritage(
    val id: Int,
    val name: String,
    val imageURL: String?,
)

fun Heritage.toSimpleHeritage(): SimpleHeritage {
    return SimpleHeritage(
        id = id,
        name = name,
        imageURL = imageURL,
    )
}