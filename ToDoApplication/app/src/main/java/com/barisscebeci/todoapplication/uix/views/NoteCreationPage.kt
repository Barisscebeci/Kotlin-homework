package com.barisscebeci.todoapplication.uix.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barisscebeci.todoapplication.uix.viewmodel.NoteCreationViewModel
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.barisscebeci.todoapplication.data.entity.ToDo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCreationPage(navController: NavController, noteCreationViewModel: NoteCreationViewModel) {

    // Görev başlığı, açıklaması ve önceliği için yerel durumlar oluşturuluyor.
    var todoTitle by remember { mutableStateOf(TextFieldValue("")) }
    var todoDescription by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Add New Task", color = Color.White, style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 20.sp)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
                    .padding(paddingValues)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Görev başlığı giriş alanı
                    OutlinedTextField(
                        value = todoTitle,
                        onValueChange = { todoTitle = it },
                        label = { Text("Task Title", style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 14.sp)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = MaterialTheme.shapes.medium
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Görev açıklaması giriş alanı
                    OutlinedTextField(
                        value = todoDescription,
                        onValueChange = { todoDescription = it },
                        label = { Text("Task Description", style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 14.sp)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        maxLines = 4,
                        shape = MaterialTheme.shapes.medium
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                    Spacer(modifier = Modifier.height(24.dp))

                    // Görevi ekle butonu
                    Button(
                        onClick = {

                            val newToDo = ToDo(
                                title = todoTitle.text,
                                description = todoDescription.text,
                                isDone = 0,
                                id = 0
                            )
                            noteCreationViewModel.insertToDo(newToDo)
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Add Task", style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 16.sp))
                    }
                }
            }
        }
    )
}
