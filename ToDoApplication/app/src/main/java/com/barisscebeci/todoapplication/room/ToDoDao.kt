package com.barisscebeci.todoapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barisscebeci.todoapplication.data.entity.ToDo

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todos: ToDo)

    @Update
    suspend fun updateToDo(todos: ToDo)

    @Delete
    suspend fun deleteToDo(todos: ToDo)

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAllToDo(): List<ToDo>

    @Query("SELECT * FROM todos WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchToDo(query: String): List<ToDo>

    @Query("UPDATE todos SET isDone = :isDone WHERE id = :nId")
    suspend fun updateToDoComp(nId: Int, isDone: Int)
}