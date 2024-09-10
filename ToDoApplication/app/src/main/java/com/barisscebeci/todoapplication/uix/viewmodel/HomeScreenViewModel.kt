package com.barisscebeci.todoapplication.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barisscebeci.todoapplication.data.entity.ToDo
import com.barisscebeci.todoapplication.data.repo.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(var trepo: ToDoRepository) : ViewModel() {
    var todosList = MutableLiveData<List<ToDo>>()

    init {
        loadToDos()
    }

    fun loadToDos() {
        CoroutineScope(Dispatchers.Main).launch {
            todosList.value = trepo.getAllToDo()
        }
    }

    fun searchToDo(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            todosList.value = trepo.searchToDo(query)
        }
    }

    fun updateToDoComp(nId: Int, isDone: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            trepo.updateToDoComp(nId, isDone)
            loadToDos()
        }
    }

    fun deleteToDo(todos: ToDo) {
        CoroutineScope(Dispatchers.Main).launch {
            trepo.deleteToDo(todos)
            loadToDos()
        }
    }
}