package com.barisscebeci.todoapplication.data.repo

import com.barisscebeci.todoapplication.data.datasource.ToDoDataSource
import com.barisscebeci.todoapplication.data.entity.ToDo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoRepository @Inject constructor(var tds: ToDoDataSource) {

    suspend fun insertToDo(todos: ToDo) = tds.insertToDo(todos)

    suspend fun updateToDo(todos: ToDo) = tds.updateToDo(todos)

    suspend fun deleteToDo(todos: ToDo) = tds.deleteToDo(todos)

    suspend fun getAllToDo(): List<ToDo> = tds.getAllToDo()

    suspend fun searchToDo(query: String): List<ToDo> = tds.searchToDo(query)

    suspend fun updateToDoComp(nId: Int, isDone: Int) = tds.updateToDoComp(nId, isDone)

}