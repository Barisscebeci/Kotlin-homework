package com.barisscebeci.todoapplication.di

import android.content.Context
import androidx.room.Room
import com.barisscebeci.todoapplication.data.datasource.ToDoDataSource
import com.barisscebeci.todoapplication.data.repo.ToDoRepository
import com.barisscebeci.todoapplication.room.ToDoDao
import com.barisscebeci.todoapplication.room.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideToDoDataSource(tdao: ToDoDao): ToDoDataSource {
        return ToDoDataSource(tdao)
    }

    @Provides
    @Singleton

    fun provideToDoRepository(tds: ToDoDataSource): ToDoRepository {
        return ToDoRepository(tds)
    }


    @Provides
    @Singleton
    fun provideToDoDao(@ApplicationContext appContext: Context): ToDoDao {
        val vt = Room
            .databaseBuilder(
                context = appContext,
                ToDoDatabase::class.java,
                "todos.sqlite"
            )
            .createFromAsset("todos.sqlite")
            .build()
        return vt.toDoDao()

    }

}