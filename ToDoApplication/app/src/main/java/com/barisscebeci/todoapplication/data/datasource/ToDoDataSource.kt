package com.barisscebeci.todoapplication.data.datasource

import com.barisscebeci.todoapplication.data.entity.ToDo
import com.barisscebeci.todoapplication.room.ToDoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDoDataSource(val tds: ToDoDao) {

    suspend fun updateToDo(todos: ToDo) {
        tds.updateToDo(todos)
    }

    suspend fun insertToDo(todos: ToDo) {
        tds.insertToDo(todos)
    }

    suspend fun deleteToDo(todos: ToDo) {
        tds.deleteToDo(todos)
    }

    suspend fun getAllToDo(): List<ToDo> = withContext(Dispatchers.IO) {
        return@withContext tds.getAllToDo()
    }

    suspend fun searchToDo(query: String): List<ToDo> = withContext(Dispatchers.IO) {
        return@withContext tds.searchToDo(query)
    }

    suspend fun updateToDoComp(nId: Int, isDone: Int) {
        tds.updateToDoComp(nId, isDone)
    }
}