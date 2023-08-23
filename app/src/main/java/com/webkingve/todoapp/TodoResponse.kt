package com.webkingve.todoapp

data class TodoResponse(
    var userid: Int,
    var id: Int,
    var title: String,
    var completed: Boolean,
)
