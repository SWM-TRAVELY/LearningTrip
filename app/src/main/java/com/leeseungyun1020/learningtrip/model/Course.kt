package com.leeseungyun1020.learningtrip.model

class Course(val id: String, val name: String, val placeList: List<SimplePlace>)
/*
class SimpleRoute(val id: String, val name: String, val image: String)

fun Route.toSimpleRoute(): SimpleRoute {
    return SimpleRoute(id, name, placeList[0].image)
}
*/