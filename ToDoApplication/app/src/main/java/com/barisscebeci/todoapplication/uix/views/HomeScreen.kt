package com.barisscebeci.todoapplication.uix.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barisscebeci.todoapplication.data.entity.ToDo
import com.barisscebeci.todoapplication.ui.theme.AppBarColor
import com.barisscebeci.todoapplication.uix.component.TodoItem
import com.barisscebeci.todoapplication.uix.viewmodel.HomeScreenViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeScreenViewModel) {
    // Görev listesini LiveData olarak gözlemler
    val taskList by homeViewModel.todosList.observeAsState(listOf())

    // Arama durumunu ve arama metnini takip eden state'ler
    val searchStatus = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }

    // Ekran yüklendiğinde görevleri yükler
    LaunchedEffect(key1 = true) {
        homeViewModel.loadToDos()
    }

    Scaffold(
        topBar = {
            // Üst bar (TopAppBar), başlık ve arama işlevini içerir
            TopAppBar(
                title = {
                    if (searchStatus.value) {
                        // Arama durumu açık ise arama çubuğu gösterilir
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                homeViewModel.searchToDo(it) // Arama sonuçlarını getirir
                            },
                            label = {
                                Text(
                                    text = "Ara",
                                    style = TextStyle(
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 16.sp,
                                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                    )
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.White,
                            )
                        )
                    } else {
                        // Arama durumu kapalıysa başlık gösterilir
                        Text(
                            "My Tasks",
                            style = TextStyle(fontFamily = FontFamily.Serif, fontSize = 24.sp),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppBarColor,
                    titleContentColor = Color.White
                ),
                actions = {

                    IconButton(onClick = {
                        searchStatus.value = !searchStatus.value
                        if (!searchStatus.value) {
                            tf.value = ""
                        }
                    }) {
                        Icon(
                            imageVector = if (searchStatus.value) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = if (searchStatus.value) "Close" else "Search",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {

            FloatingActionButton(
                onClick = { navController.navigate("addTaskScreen") },
                containerColor = Color.Transparent
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                    tint = Color.White
                )
            }
        },
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White) // Arka plan rengi
                    .padding(paddingValues)
            ) {
                ToDoList(
                    todosList = taskList,
                    onToDoClick = { todos ->
                        val todosJson = Gson().toJson(todos)
                        navController.navigate("notedetailspage/$todosJson") //
                    },
                    onToDoCompletedChange = { todos, isDone ->
                        homeViewModel.updateToDoComp(
                            todos.id,
                            if (isDone) 1 else 0
                        ) // Görevin tamamlanma durumu güncellenir
                    },
                    onToDoDelete = { todos -> homeViewModel.deleteToDo(todos) } // Görev silinir
                )
            }
        }
    )
}


@Composable
fun ToDoList(
    todosList: List<ToDo>,
    onToDoClick: (ToDo) -> Unit,
    onToDoCompletedChange: (ToDo, Boolean) -> Unit,
    onToDoDelete: (ToDo) -> Unit
) {
    val incompleteTasks = todosList.filter { it.isDone == 0 }
    val completedTasks = todosList.filter { it.isDone == 1 }

    if (todosList.isEmpty()) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                "No tasks available.",
                style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 18.sp)
            )
        }
    } else {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(incompleteTasks) { todos ->
                TodoItem(
                    todos = todos,
                    onTodoClick = { onToDoClick(todos) },
                    onToDoCompChange = { onToDoCompletedChange(todos, it) },
                    onTodoDelete = { onToDoDelete(todos) }
                )
            }
            items(completedTasks) { todos ->
                TodoItem(
                    todos = todos,
                    onTodoClick = { onToDoClick(todos) },
                    onToDoCompChange = { onToDoCompletedChange(todos, it) },
                    onTodoDelete = { onToDoDelete(todos) }
                )
            }
        }
    }
}
