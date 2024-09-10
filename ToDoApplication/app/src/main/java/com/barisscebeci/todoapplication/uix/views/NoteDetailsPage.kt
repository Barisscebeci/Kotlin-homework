package com.barisscebeci.todoapplication.uix.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barisscebeci.todoapplication.data.entity.ToDo
import com.barisscebeci.todoapplication.uix.viewmodel.NoteDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsPage(navController: NavController, noteDetailsViewModel: NoteDetailsViewModel, todosObj: ToDo) {

    var todosTitle by remember { mutableStateOf(TextFieldValue(todosObj.title)) }
    var todosDescription by remember { mutableStateOf(TextFieldValue(todosObj.description)) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Todo Details",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF8E24AA),
                    titleContentColor = Color.White
                ),
                modifier = Modifier.shadow(4.dp)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),

                        ) {
                        OutlinedTextField(
                            value = todosTitle,
                            onValueChange = { todosTitle = it },
                            label = {
                                Text(
                                    "Todo Title",
                                    style = TextStyle(
                                        fontFamily = FontFamily.Default,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFF8E24AA),
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),

                        ) {
                        OutlinedTextField(
                            value = todosDescription,
                            onValueChange = { todosDescription = it },
                            label = {
                                Text(
                                    "Type the Todo",
                                    style = TextStyle(
                                        fontFamily = FontFamily.Default,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            maxLines = 5,
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFF8E24AA),
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            val updatedTodos = todosObj.copy(
                                title = todosTitle.text,
                                description = todosDescription.text
                            )
                            noteDetailsViewModel.updateToDo(updatedTodos)
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8E24AA),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
                    ) {
                        Text(
                            "Update Todo",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    )
}
