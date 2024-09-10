package com.barisscebeci.todoapplication.uix.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.barisscebeci.todoapplication.data.entity.ToDo
import com.barisscebeci.todoapplication.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(
    private val trepo: ToDoRepository // Repository doğru şekilde tanımlandı
) : ViewModel() {
    fun updateToDo(todos: ToDo) {
        CoroutineScope(Dispatchers.Main).launch {
            trepo.updateToDo(todos) // Veritabanı güncelleme işlemi repository üzerinden yapılır
        }
    }
}