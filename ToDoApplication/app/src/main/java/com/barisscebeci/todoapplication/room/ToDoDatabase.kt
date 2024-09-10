package com.barisscebeci.todoapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barisscebeci.todoapplication.data.entity.ToDo


@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao() : ToDoDao
}